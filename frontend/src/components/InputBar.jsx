import React from 'react';
import './InputBar.css';

export default function InputBar({ onSend, loading }) {
  const [input, setInput] = React.useState('');

  const handleSend = () => {
    if (input.trim() && !loading) {
      onSend(input);
      setInput('');
    }
  };

  const handleKeyPress = (e) => {
    if (e.key === 'Enter' && !e.shiftKey && !loading) {
      handleSend();
    }
  };

  return (
    <div className="input-bar">
      <div className="input-container">
        <textarea
          value={input}
          onChange={(e) => setInput(e.target.value)}
          onKeyPress={handleKeyPress}
          placeholder="Ask about weather, set a reminder, or create an alert..."
          rows="1"
          className="input-field"
        />
        <button
          onClick={handleSend}
          disabled={!input.trim() || loading}
          className="send-btn"
        >
          {loading ? (
            <span className="spinner">⟳</span>
          ) : (
            <span>➤</span>
          )}
        </button>
      </div>
      <p className="input-hint">
        💡 Try: "What's the weather in Delhi?" or "Remind me to gym at 4pm"
      </p>
    </div>
  );
}
