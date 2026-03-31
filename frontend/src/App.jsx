import React from 'react';
import ChatWindow from './components/ChatWindow';
import InputBar from './components/InputBar';
import Sidebar from './components/Sidebar';
import { chatApi } from './api';
import './App.css';

export default function App() {
  const [messages, setMessages] = React.useState([]);
  const [loading, setLoading] = React.useState(false);
  const [weather, setWeather] = React.useState(null);
  const [reminders, setReminders] = React.useState([]);
  const [alerts, setAlerts] = React.useState([]);

  const handleSendMessage = async (text) => {
    // Add user message to chat
    setMessages(prev => [...prev, { type: 'user', text }]);
    setLoading(true);

    try {
      // Send message to backend
      const response = await chatApi.sendMessage(text);
      const aiMessage = response.data.reply || response.data || 'No response from server';
      
      // Add AI response
      setMessages(prev => [...prev, { type: 'assistant', text: aiMessage }]);

      // Extract and store weather data if applicable
      if (text.toLowerCase().includes('weather') || text.toLowerCase().includes('temp')) {
        const tempMatch = aiMessage.match(/(\d+)/);
        if (tempMatch) {
          const cityMatch = text.match(/(?:weather|temp)(?:\s+in)?\s+(\w+)/i);
          setWeather({
            city: cityMatch ? cityMatch[1] : 'Unknown',
            temp: parseInt(tempMatch[0])
          });
        }
      }

      // Extract reminder if user asked to set one
      if (text.toLowerCase().includes('remind')) {
        const timeMatch = text.match(/(\d{1,2}):?(\d{2})?\s?(am|pm)?/i);
        if (timeMatch) {
          const reminder = {
            message: text.replace(/remind|set|alarm/gi, '').trim() || 'Reminder',
            time: timeMatch[0]
          };
          setReminders(prev => [...prev, reminder]);
        }
      }

      // Extract alert if user asked to create one
      if (text.toLowerCase().includes('alert')) {
        const cityMatch = text.match(/(?:alert|for)\s+(\w+)/i);
        const tempMatch = text.match(/(\d+)\s*°?C?/i);
        if (cityMatch && tempMatch) {
          const alert = {
            city: cityMatch[1],
            threshold: tempMatch[1]
          };
          setAlerts(prev => [...prev, alert]);
        }
      }

    } catch (error) {
      console.error('Error:', error);
      const errorMessage = error.response?.data?.message || error.message || 'Failed to connect to server';
      setMessages(prev => [...prev, {
        type: 'assistant',
        text: '❌ Error: ' + errorMessage
      }]);
    } finally {
      setLoading(false);
    }
  };

  const handleAddReminder = async (reminder) => {
    try {
      // Convert time HH:mm to ISO format yyyy-MM-ddTHH:mm:ss
      const now = new Date();
      const [hours, minutes] = reminder.time.split(':');
      const isoTime = new Date(
        now.getFullYear(),
        now.getMonth(),
        now.getDate(),
        hours,
        minutes
      ).toISOString().slice(0, 19);

      const response = await chatApi.setReminder(reminder.message, isoTime);
      setReminders(prev => [...prev, reminder]);

      // Add system message
      setMessages(prev => [...prev, {
        type: 'assistant',
        text: `✅ Reminder set: "${reminder.message}" at ${reminder.time}`
      }]);
    } catch (error) {
      console.error('Error setting reminder:', error);
      setMessages(prev => [...prev, {
        type: 'assistant',
        text: `❌ Failed to set reminder: ${error.message}`
      }]);
    }
  };

  const handleAddAlert = async (alert) => {
    try {
      const response = await chatApi.setAlert(alert.city, parseFloat(alert.threshold));
      setAlerts(prev => [...prev, alert]);

      // Add system message
      setMessages(prev => [...prev, {
        type: 'assistant',
        text: `✅ Alert created: ${alert.city} will notify when temp exceeds ${alert.threshold}°C`
      }]);
    } catch (error) {
      console.error('Error setting alert:', error);
      setMessages(prev => [...prev, {
        type: 'assistant',
        text: `❌ Failed to create alert: ${error.message}`
      }]);
    }
  };

  return (
    <div className="app">
      <div className="app-header">
        <div className="header-content">
          <h1>🤖 Weather AI Bot</h1>
          <p>Powered by Groq Llama 3.1</p>
        </div>
      </div>

      <div className="app-container">
        <div className="main-content">
          <ChatWindow messages={messages} loading={loading} />
          <InputBar onSend={handleSendMessage} loading={loading} />
        </div>

        <Sidebar
          weather={weather}
          reminders={reminders}
          alerts={alerts}
          onAddReminder={handleAddReminder}
          onAddAlert={handleAddAlert}
        />
      </div>
    </div>
  );
}
