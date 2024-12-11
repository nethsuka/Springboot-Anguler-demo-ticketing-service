
# Real-Time Event Ticketing System

This is a demo project of a real-time event ticketing system that utilizes advanced multithreading and the Producer-Consumer pattern to manage concurrent ticket releases by vendors and purchases by customers. It demonstrates practical implementations of concurrency, synchronization, and system design principles. The system ensures smooth handling of multiple vendors releasing tickets and customers purchasing them simultaneously. Both CLI (Command-Line Interface) and GUI (Graphical User Interface) are integrated into the system. The CLI allows users to interact via text-based commands, while the GUI offers a visual interface for a more intuitive user experience. This combination of interfaces enhances accessibility and usability for various user preferences.


## Features

- **Concurrency Handling** - Vendors and customers interact with the shared ticket pool concurrently.

- **Thread Safety** - Proper synchronization ensures data integrity and avoids race conditions.

- **Command Line Interface (CLI)** - Allows for system configuration and interaction through commands.

- **GUI Interface** - Allows for system configuration and test multithreading.

- **API Endpoints** - Expose system functionality for external integration.

- **Logging** - Logs system activities for monitoring and debugging.


## Prerequisites

Ensure you have the following installed on your system:

- Java Development Kit (JDK) 21
- Apache Maven
- Spring Boot framework
- Anguler
- Node.js and npm (if using the optional frontend with Angular)
- Git
## Getting Started

1. **Clone the Repository**

```bash
git clone <repository_url>
cd Springboot-Anguler-demo-ticketing-service
```

2. **Build the Backend Application**

Navigate to the backend directory:
```bash
cd springboot-ticketing-service
mvn clean install
```
3. **Run the Spring-Boot Application**

To Start the Spring Boot backend application run the `TicketBookingApplication.java` file. Then it will run the Tomcat surver. 

4. **Run the CLI Part**

Access the javaCLI folder which is inside the src folder, and run the `ApplicationConfig.java` file, Then it will run the CLI program.

5. **Run the Angular Frontend**
Navigate to the frontend directory and serve the application:
```bash
cd ticketBookingAplication
npm install
ng serve
```
Access the application at `http://localhost:4200`.
## Command-Line Interface (CLI)

The CLI supports the following commands for system configuration and control:

- Configure system:
  
  ```bash
  Enter command>> config
  ```
- Save configuration:
    ```bash
    Enter command>> save-config
    ```
- To see configuration:
  
  ```bash
  Enter command>> show-config
  ```
- Strat program:
  ```bash
  Enter command>> start
  ```
    This will start running threads.

- Chech available commands:
  
  ```bash
  Enter command>> help
  ```
- End the progarm:
  
  ```bash
  Enter command>> exit
  ```

  
## API Reference

#### Get configuration data

```http
  GET /api/config-data
```

#### Post configuration data

```http
  POST /api/config-data
```
#### Start program request

```http
  GET /api/start
```
#### Strop program request

```http
  GET /api/stop
```
#### Get output massages

```http
  GET /api/outputs
```

#### Get sould ticket count

```http
  GET /api/sould-tickets
```



## Project Structure

- **Backend**: Spring Boot application managing core logic and APIs.
- **Frontend**: Angular application for visualizing the system.
- **CLI**: core java CLI application that represents demo functionalities
- **Common**: Shared models and utilities.
## Screenshots

#### GUI Interface
![App Screenshot](https://i.ibb.co/7Ww17DK/fonr.png)

#### CLI Interface
![App Screenshot](https://i.ibb.co/JpjhPrj/clip.png)

#### Spring-Boot Interface
![App Screenshot](https://i.ibb.co/12CngPx/backend.png)
