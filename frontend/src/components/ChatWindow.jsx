import React from 'react';
import './ChatWindow.css';

export default function ChatWindow({ messages, loading }) {
  const messagesEndRef = React.useRef(null);

  React.useEffect(() => {
    messagesEndRef.current?.scrollIntoView({ behavior: 'smooth' });
  }, [messages]);

  return (
    <div className="chat-window">
      <div className="chat-messages">
        {messages.length === 0 ? (
          <div className="chat-empty">
            <div className="empty-icon">🤖</div>
            <h2>Weather AI Assistant</h2>
            <p>Ask me about weather, set reminders, or create alerts</p>
          </div>
        ) : (
          messages.map((msg, idx) => (
            <div key={idx} className={`message ${msg.type}`}>
              <div className="message-icon">
                {msg.type === 'user' ? '👤' : '🤖'}
              </div>
              <div className="message-content">
                {msg.text}
              </div>
            </div>
          ))
        )}
        {loading && (
          <div className="message assistant">
            <div className="message-icon">🤖</div>
            <div className="message-content">
              <div className="typing">
                <span></span>
                <span></span>
                <span></span>
              </div>
            </div>
          </div>
        )}
        <div ref={messagesEndRef} />
      </div>
    </div>
  );
}
