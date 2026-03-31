import axios from 'axios';

const API_BASE = 'http://localhost:9100/api';

const api = axios.create({
  baseURL: API_BASE,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const chatApi = {
  // Send message to chat (handles weather, reminders, general queries)
  sendMessage: (message) => 
    api.post('/chat', { message }),
  
  // Set a reminder
  setReminder: (message, time) => 
    api.post('/reminder', { message, time }),
  
  // Create a temperature alert
  setAlert: (city, threshold) => 
    api.post('/alert', { city, threshold }),
};

export default api;

