import React from 'react';
import './Sidebar.css';

export default function Sidebar({ weather, reminders, alerts, onAddReminder, onAddAlert }) {
  const [reminderForm, setReminderForm] = React.useState({ message: '', time: '' });
  const [alertForm, setAlertForm] = React.useState({ city: '', threshold: '' });

  const handleAddReminder = () => {
    if (reminderForm.message && reminderForm.time) {
      onAddReminder(reminderForm);
      setReminderForm({ message: '', time: '' });
    }
  };

  const handleAddAlert = () => {
    if (alertForm.city && alertForm.threshold) {
      onAddAlert(alertForm);
      setAlertForm({ city: '', threshold: '' });
    }
  };

  return (
    <div className="sidebar">
      {/* Weather Widget */}
      <div className="widget">
        <div className="widget-header">
          <h3>🌤️ Weather</h3>
        </div>
        <div className="widget-content">
          {weather ? (
            <>
              <div className="weather-main">
                <div className="temp">{weather.temp}°C</div>
                <div className="city">{weather.city}</div>
              </div>
              <div className="weather-detail">
                <p>Feels like {weather.temp}°C</p>
              </div>
            </>
          ) : (
            <p className="empty-state">No weather data yet</p>
          )}
        </div>
      </div>

      {/* Reminders Widget */}
      <div className="widget">
        <div className="widget-header">
          <h3>⏰ Reminders</h3>
        </div>
        <div className="widget-content">
          {reminders.length > 0 ? (
            <div className="items-list">
              {reminders.map((reminder, idx) => (
                <div key={idx} className="item">
                  <span className="item-text">{reminder.message}</span>
                  <span className="item-time">{reminder.time}</span>
                </div>
              ))}
            </div>
          ) : (
            <p className="empty-state">No reminders set</p>
          )}
          
          <div className="form-group">
            <input
              type="text"
              placeholder="📝 What to remind?"
              value={reminderForm.message}
              onChange={(e) => setReminderForm({ ...reminderForm, message: e.target.value })}
              className="form-input"
            />
            <input
              type="time"
              value={reminderForm.time}
              onChange={(e) => setReminderForm({ ...reminderForm, time: e.target.value })}
              className="form-input"
            />
            <button onClick={handleAddReminder} className="form-btn">
              ✨ Add Reminder
            </button>
          </div>
        </div>
      </div>

      {/* Alerts Widget */}
      <div className="widget">
        <div className="widget-header">
          <h3>🚨 Weather Alerts</h3>
        </div>
        <div className="widget-content">
          {alerts.length > 0 ? (
            <div className="items-list">
              {alerts.map((alert, idx) => (
                <div key={idx} className="alert-item">
                  <span className="item-text">{alert.city}</span>
                  <span className="threshold">{alert.threshold}°C</span>
                </div>
              ))}
            </div>
          ) : (
            <p className="empty-state">No alerts set</p>
          )}
          
          <div className="form-group">
            <input
              type="text"
              placeholder="📍 City name"
              value={alertForm.city}
              onChange={(e) => setAlertForm({ ...alertForm, city: e.target.value })}
              className="form-input"
            />
            <input
              type="number"
              placeholder="🌡️ Temp threshold (°C)"
              value={alertForm.threshold}
              onChange={(e) => setAlertForm({ ...alertForm, threshold: e.target.value })}
              className="form-input"
            />
            <button onClick={handleAddAlert} className="form-btn">
              🚨 Create Alert
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
