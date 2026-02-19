# API Monitoring

A Java application that monitors API endpoints and sends email notifications for failed APIs.

## Overview

This application reads a list of APIs from an Excel file, checks their status, and sends email notifications for any failed endpoints. It's designed to help monitor the health of multiple API endpoints automatically.

## Features

- **API Monitoring**: Checks the status of multiple API endpoints
- **Excel Integration**: Reads API configurations from Excel files
- **Email Notifications**: Sends automated email reports for failed APIs
- **Maven-based**: Built with Maven for dependency management

## Dependencies

- **REST Assured** (6.0.0): For API testing and validation
- **Jakarta Mail** (2.2.0-M1): For email functionality
- **Apache POI** (5.5.1): For Excel file operations
- **Apache Log4j** (2.20.0): For application logging
- **Hamcrest** (3.0): For test assertions
- **JUnit** (3.8.1): For unit testing

## Project Structure

```
src/
├── main/
│   └── java/
│       └── org/
│           └── raman/
│               ├── App.java                    # Main application entry point
│               ├── MailSender.java             # Email notification handler
│               ├── MailBody.java               # Email content formatting
│               ├── InitializingMailRequirements.java  # Mail configuration
│               ├── abstractRes/
│               │   ├── GetProperty.java        # Property file utilities
│               │   └── ExcelFileReader.java   # Excel file operations
│               ├── api/
│               │   └── monitoring/
│               │       └── CheckApiStat.java   # API status checking logic
│               └── resources/
│                   └── globalProp.properties   # Global configuration
└── test/
    └── java/           # Test classes
```

## How It Works

1. **Configuration**: The application reads configuration from `globalProp.properties`
2. **API List**: Extracts API endpoints from an Excel file using `ExcelFileReader`
3. **Status Check**: Validates each API using `CheckApiStat`
4. **Notification**: If any APIs fail, sends email report via `MailSender`

## Setup and Installation

### Prerequisites

- Java 8 or higher
- Maven 3.6 or higher
- Excel file with API configurations

### Building the Project

```bash
mvn clean compile
```

### Running the Application

```bash
mvn exec:java -Dexec.mainClass="org.raman.App"
```

### Running Tests

```bash
mvn test
```

## Configuration

Configure the application properties in `src/main/java/org/raman/resources/globalProp.properties`:

- Email server settings
- API timeout configurations
- Excel file path
- Notification preferences

## Usage

1. Prepare an Excel file with the list of APIs to monitor
2. Configure the application properties
3. Run the application
4. Check your email for failure notifications (if any APIs fail)

## Deployment

### Creating a JAR File

Build the executable JAR using Maven:

```bash
mvn clean package
```

This will create a JAR file in the `target/` directory.

### Setting up Cron Job

1. **Copy the JAR** to your deployment location
2. **Create a shell script** (optional but recommended):

```bash
#!/bin/bash
cd /path/to/your/application
java -jar target/api-monitoring-1.0-SNAPSHOT.jar
```

3. **Add to crontab**:

```bash
crontab -e
```

4. **Schedule the monitoring** (examples):

```bash
# Run every 30 minutes
*/30 * * * * /path/to/your/script.sh

# Run every hour at minute 0
0 * * * * /path/to/your/script.sh

# Run every weekday at 9 AM
0 9 * * 1-5 /path/to/your/script.sh
```

### Production Considerations

- **Logging**: Ensure log4j2.xml is configured for production logs
- **Error handling**: Monitor logs for any runtime errors
- **Resource limits**: Set appropriate memory limits for the JVM
- **Backup**: Keep backups of your Excel API configuration file
- **Email reliability**: Test email delivery from your production environment

### Systemd Service (Alternative to Cron)

For more robust process management, consider creating a systemd service:

```ini
[Unit]
Description=API Monitoring Service
After=network.target

[Service]
User=your-user
WorkingDirectory=/path/to/your/application
ExecStart=/usr/bin/java -jar target/api-monitoring-1.0-SNAPSHOT.jar
Restart=always
RestartSec=10

[Install]
WantedBy=multi-user.target
```

## License

This project is licensed under the Apache License 2.0.
