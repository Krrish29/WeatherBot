# 🌦️ WeatherBot - AI Weather Assistant

> An intelligent weather bot with email notifications, reminders, and temperature alerts powered by AI

**🌐 [Live Demo](https://krrish29.github.io/WeatherBot)** - Click to try it out!

## ✨ Features

- 🤖 **AI-Powered Chat** - Intelligent conversations using Groq Llama 3.1 8B
- 🌍 **Weather Queries** - Get real-time weather for any city
- 📧 **Email Notifications** - Receive weather updates & reminders via email
- 🔔 **Smart Reminders** - Set reminders with automatic email notifications
- 🌡️ **Temperature Alerts** - Monitor temperature thresholds and get alerts
- 🎨 **Dark Theme UI** - Beautiful GitHub-style dark interface
- 📱 **Responsive Design** - Works on desktop and mobile

## 🛠️ Tech Stack

**Frontend:**
- React 18.2
- Vite 5.4.21
- Axios for API calls
- CSS3 styling

**Backend:**
- Spring Boot 4.0.3
- Java 17
- Groq AI (Llama 3.1 8B)
- OpenWeatherMap API
- Gmail SMTP for emails

## 🚀 Quick Start

### Prerequisites
- Node.js 16+
- Java 17+
- Maven
- Groq API Key
- Gmail app password
- OpenWeatherMap API Key

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/Krrish29/WeatherBot.git
cd WeatherBot
```

2. **Backend Setup**
```bash
# Configure application.properties
cd src/main/resources
# Edit application.properties with your API keys and credentials

# Start backend
cd ../../../
.\mvnw.cmd spring-boot:run
```

Backend runs on: `http://localhost:9100`

3. **Frontend Setup**
```bash
cd frontend
npm install
npm run dev
```

Frontend runs on: `http://localhost:3001`

## 📧 Email Configuration

Update `src/main/resources/application.properties`:

```properties
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
groq.api.key=your-groq-api-key
email.reminder.recipient=your-email@gmail.com
email.weather-alert.recipient=your-email@gmail.com
email.temperature-alert.recipient=your-email@gmail.com
email.weather-update.recipient=your-email@gmail.com
```

## 📚 API Endpoints

### Chat
```
POST /api/chat
Content-Type: application/json

{
  "message": "What's the weather in Delhi?"
}
```

### Add Reminder
```
POST /api/reminder
{
  "task": "go to gym",
  "time": "11:05"
}
```

### Add Alert
```
POST /api/alert
{
  "city": "Delhi",
  "threshold": 35
}
```

## 🎨 Email Themes

- **Reminder Emails**: Purple gradient theme
- **Weather Emails**: Cyan gradient theme
- **Temperature Alerts**: Red/Orange gradient theme
- **Generic Updates**: Green gradient theme

All emails feature dark background with professional styling.

## 📁 Project Structure

```
WeatherBot/
├── frontend/               # React app
│   ├── src/
│   │   ├── components/    # UI components
│   │   ├── App.jsx
│   │   └── api.js        # Backend API calls
│   └── vite.config.js
├── src/main/java/
│   └── org/example/agenticai/
│       ├── controller/    # REST endpoints
│       ├── service/      # Business logic
│       ├── scheduler/    # Email & alert schedulers
│       ├── config/       # Spring config
│       └── model/        # Data models
└── src/main/resources/
    └── application.properties
```

## 🧠 AI Features

### Natural Language Processing
- Understands weather queries in natural language
- Can extract city names and temperature thresholds
- Context-aware responses with emojis

### Tools
- **Weather Tool**: Fetches real-time temperature data
- **Reminder Tool**: Creates email-based reminders
- **Alert Tool**: Monitors temperature thresholds

## 🔔 Scheduler Details

- **ReminderScheduler**: Runs every 30 seconds, sends reminder emails
- **AlertScheduler**: Runs every 60 seconds, monitors user-defined alerts
- **WeatherAlertScheduler**: Runs every 60 seconds, global weather monitoring

## 🚀 Deployment

### Frontend (GitHub Pages)
```bash
cd frontend
npm run deploy
```
Live at: https://krrish29.github.io/WeatherBot

### Backend (Cloud)
Deploy to Heroku, AWS, Azure, or Railway for production use.

## 🐛 Troubleshooting

**Emails not sending?**
- Check Gmail credentials in application.properties
- Verify app password is correct (not regular password)
- Check spam folder

**Weather API not working?**
- Verify OpenWeatherMap API key
- Check internet connection
- Ensure API key has valid requests remaining

**Frontend 404 error?**
- Clear browser cache (Ctrl+Shift+Delete)
- Wait 1-2 minutes for GitHub Pages to update
- Hard refresh page (Ctrl+F5)

## 📋 Future Enhancements

- [ ] User authentication and persistence
- [ ] Multiple user support with profiles
- [ ] SMS notifications
- [ ] Weather history/analytics
- [ ] More AI models support
- [ ] Mobile app (React Native)
- [ ] Voice commands
- [ ] Calendar integration

## 📝 License

MIT License - feel free to use for your projects!

## 👤 Author

Built by Krrish29

---

**Have questions?** Open an issue on [GitHub Issues](https://github.com/Krrish29/WeatherBot/issues)
