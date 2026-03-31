# 🌤️ WeatherBot - AI-Powered Weather Monitoring System

A full-stack AI weather bot featuring intelligent weather monitoring, automated temperature alerts, reminders, and beautiful dark-themed email notifications.

## ✨ Features

✅ **AI Assistant** - Chat with Groq Llama 3.1 AI for weather queries
✅ **Weather Monitoring** - Real-time temperature tracking via OpenWeatherMap
✅ **Temperature Alerts** - Get notified when temperature exceeds your threshold
✅ **Reminders** - Set custom reminders with scheduled email notifications
✅ **Email Notifications** - Beautiful dark-themed emails (GitHub style)
✅ **Configurable Recipients** - Change email recipients via properties
✅ **Dark Theme UI** - Modern, user-friendly React frontend
✅ **REST API** - Full REST API for all features

## 🎨 Email Themes

- 🔔 **Reminders** - Purple gradient theme
- 🌤️ **Weather Updates** - Cyan/Blue theme
- 🚨 **Temperature Alerts** - Red/Orange theme
- 📧 **Generic Notifications** - Green theme

## 🏗️ Tech Stack

**Backend:**
- Spring Boot 4.0.3
- Java 17
- LangChain4j (Groq Llama 3.1)
- OpenWeatherMap API
- Gmail SMTP

**Frontend:**
- React 18.2
- Vite 5.4.21
- Dark theme CSS

## 🚀 Quick Start

### Prerequisites
- Java 17+
- Node.js 16+
- Maven
- Gmail account with app password

### 1. Clone & Setup

```bash
git clone https://github.com/Krrish29/WeatherBot.git
cd WeatherBot
```

### 2. Configure Credentials

Edit `src/main/resources/application.properties`:

```properties
# Groq API Key (https://console.groq.com)
groq.api.key=YOUR_GROQ_API_KEY

# Gmail SMTP
spring.mail.username=YOUR_EMAIL@gmail.com
spring.mail.password=YOUR_APP_PASSWORD

# Email Recipients
email.reminder.recipient=YOUR_EMAIL@gmail.com
email.weather-alert.recipient=YOUR_EMAIL@gmail.com
email.temperature-alert.recipient=YOUR_EMAIL@gmail.com
email.weather-update.recipient=YOUR_EMAIL@gmail.com
```

### 3. Start Backend

```bash
.\mvnw.cmd spring-boot:run
```

Backend runs on: **http://localhost:9100**

### 4. Start Frontend

```bash
cd frontend
npm install
npm run dev
```

Frontend runs on: **http://localhost:5173**

## 📱 Usage

### Chat with AI
Ask weather questions:
- "What's the weather in Delhi?"
- "Is it raining in Mumbai?"
- "Temperature in New York?"

### Set Temperature Alerts
```
"Alert me if Chennai exceeds 35°C"
"Alert me if temp drops below 10°C"
```

### Create Reminders
```
"Remind me to drink water at 10:00"
"Set reminder for gym at 18:30"
```

Or use the UI reminder widget with time picker.

## 🔧 API Endpoints

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
Content-Type: application/json

{
  "message": "Drink water",
  "time": "2026-03-31T10:00:00"
}
```

### Create Alert
```
POST /api/alert
Content-Type: application/json

{
  "city": "Delhi",
  "temperature": 35
}
```

## 📅 Scheduled Tasks

- **ReminderScheduler** - Checks reminders every 30 seconds
- **AlertScheduler** - Monitors temperature alerts every 60 seconds
- **WeatherAlertScheduler** - Global weather monitoring every 60 seconds

## 🔐 Security Notes

- **Never commit secrets** - Use `application.properties` with placeholders
- **Gmail App Password** - Enable 2FA and create app-specific password
- **API Keys** - Store in environment variables or properties file (not in git)

## 📧 Environment Variables

Create `.env` file (copy from `.env.example`):

```bash
GROQ_API_KEY=your_key
GMAIL_USERNAME=your_email@gmail.com
GMAIL_PASSWORD=your_app_password
```

## 🎯 Project Structure

```
WeatherBot/
├── backend/
│   ├── src/main/java/org/example/agenticai/
│   │   ├── config/         # AI & Mail configuration
│   │   ├── controller/     # REST endpoints
│   │   ├── service/        # Business logic
│   │   ├── scheduler/      # Scheduled tasks
│   │   ├── model/          # Data models
│   │   ├── dto/            # Request/Response
│   │   └── tools/          # AI tools
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
├── frontend/
│   ├── src/
│   │   ├── components/     # React components
│   │   ├── App.jsx         # Main app
│   │   └── main.jsx        # Entry point
│   ├── package.json
│   └── vite.config.js
└── README.md
```

## 🐛 Troubleshooting

**Backend won't start:**
- Check Java 17 is installed: `java -version`
- Verify port 9100 is not in use
- Check API keys in `application.properties`

**Emails not sending:**
- Verify Gmail app password (not regular password)
- Enable "Less secure app access" if needed
- Check SMTP configuration

**AI responses not working:**
- Verify Groq API key is valid
- Check LLM model name (default: llama-3.1-8b-instant)
- Ensure internet connection

## 📝 Future Enhancements

- [ ] Database integration (PostgreSQL)
- [ ] Persistent reminder/alert storage
- [ ] Multi-user support with authentication
- [ ] Mobile app (React Native)
- [ ] SMS notifications
- [ ] Weather history & analytics
- [ ] Integration with weather APIs (Dark Sky, etc.)

## 📄 License

MIT License - Feel free to use and modify!

## 👨‍💻 Author

Developed with ❤️ by Krrish Garg

---

Made with ☕ and 🤖
