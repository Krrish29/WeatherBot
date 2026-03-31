# Weather AI Bot - React Frontend

A modern, ChatGPT-like React frontend for your Weather AI Bot.

## Features

✨ **Modern UI**
- Sleek chat interface inspired by ChatGPT
- Real-time message updates with smooth animations
- Responsive design for desktop and mobile

🌤️ **Weather Widget**
- Real-time temperature display
- City-specific weather queries
- Visual weather indicators

⏰ **Reminder Manager**
- Set reminders with custom messages and times
- Automatic email notifications
- View all active reminders

🚨 **Weather Alerts**
- Create temperature threshold alerts
- Monitor multiple cities
- Receive notifications when thresholds are exceeded

## Installation

### Prerequisites
- Node.js 16+ and npm

### Setup

1. Install dependencies:
```bash
cd frontend
npm install
```

2. Make sure the backend is running on `http://localhost:9100`

## Running the Frontend

### Development Mode
```bash
npm run dev
```
The application will be available at `http://localhost:3000`

### Production Build
```bash
npm run build
npm run preview
```

## Project Structure

```
frontend/
├── src/
│   ├── components/
│   │   ├── ChatWindow.jsx         # Main chat interface
│   │   ├── InputBar.jsx           # Message input field
│   │   ├── Sidebar.jsx            # Weather, reminders, alerts
│   │   └── styles.css
│   ├── App.jsx                    # Main application component
│   ├── App.css
│   ├── api.js                     # Backend API calls
│   ├── index.css                  # Global styles
│   └── main.jsx                   # Entry point
├── public/
│   └── index.html
├── package.json
└── vite.config.js
```

## Usage

1. **Chat with AI**
   - Type messages to interact with the weather AI bot
   - Use natural language like "What's the weather in Delhi?"

2. **Set Reminders**
   - Click the reminder widget
   - Enter message and time
   - Get email notifications at the scheduled time

3. **Create Alerts**
   - Click the alerts widget
   - Select city and temperature threshold
   - Receive alerts when temperature exceeds the threshold

## API Endpoints Used

- `POST /api/chat` - Send chat messages
- `GET /api/weather?city=<city>` - Get weather for a city
- `POST /api/reminder` - Create a reminder
- `POST /api/alert` - Create a temperature alert

## Technologies Used

- **React 18** - UI framework
- **Vite** - Build tool and dev server
- **Axios** - HTTP client
- **CSS3** - Styling with animations and gradients

## Customization

### Change Backend URL
Edit `src/api.js`:
```javascript
const API_BASE = 'http://your-backend-url/api';
```

### Modify Colors
Edit `src/App.css` and component CSS files to change the color scheme.

### Add More Widgets
Create new component files in `src/components/` and import them in `App.jsx`.

## Troubleshooting

### CORS Issues
If you see CORS errors, ensure the backend has CORS enabled:
```java
@CrossOrigin(origins = "*")
public class YourController { ... }
```

### Cannot connect to backend
- Verify backend is running on port 9100
- Check the API_BASE URL in `src/api.js`
- Ensure no firewall is blocking the connection

## License

MIT
