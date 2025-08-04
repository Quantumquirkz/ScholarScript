# Knock Knock Server & Client

This repository contains a simple implementation of a **Knock Knock joke server** using Python's `socket` module. The server sends a Knock Knock joke to the client, and the client responds following the typical format of a Knock Knock joke.

## Table of Contents
- [Introduction](#introduction)
- [Requirements](#requirements)
- [Setup](#setup)
- [Usage](#usage)
  - [Server](#server)
  - [Client](#client)
- [How It Works](#how-it-works)
- [Contributing](#contributing)
- [License](#license)

## Introduction
The Knock Knock server sends jokes to connected clients. The client responds with the typical Knock Knock dialogue:  
- Server: **Toc toc**  
- Client: **¿Quién es?**  
- Server: [Random Joke Setup]  
- Client: [Setup] **quién?**  
- Server: [Punchline]  

This simple exchange is done using socket programming where the server sends and receives data from a client over a TCP connection.

## Requirements
To run this project, you need:
- Python 3.x
- Basic knowledge of socket programming

## Setup
1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/knock-knock-server.git
    cd knock-knock-server
    ```
2. Ensure you have Python 3.x installed. You can check your version using:
    ```bash
    python --version
    ```

## Usage

### Server
1. Run the server by executing the following command:
    ```bash
    python server.py
    ```
   This will start the server and it will listen for connections on `localhost:8000`.

### Client
2. After starting the server, open another terminal and run the client:
    ```bash
    python client.py
    ```
   The client will connect to the server, and the Knock Knock joke interaction will begin.

### Expected Output:
When you run the server and client, you can expect an interaction like the following:

**Server terminal**:

## How It Works
This project consists of two components:

### Server
The server script creates a TCP socket that listens on `localhost` at port `8000`. When a client connects:
1. It sends the initial "Toc toc" message.
2. It waits for the client to respond with "¿Quién es?".
3. The server then randomly selects one of the pre-defined Knock Knock jokes and sends the setup (e.g., "Lechuga").
4. The client responds with "[Setup] quién?", and the server replies with the punchline (e.g., "Lechuga adentro que está lloviendo!").
5. If the client does not follow the expected protocol, the server terminates the connection.

### Client
The client script creates a TCP socket that connects to the server at `localhost:8000`. Once connected:
1. It receives the first message from the server ("Toc toc").
2. It sends "¿Quién es?" back to the server.
3. It receives the joke setup (e.g., "Lechuga") and responds with "[Setup] quién?".
4. Finally, it receives the punchline from the server and displays it.

### Example Joke Data Structure:
```python
knock_knock_jokes = [
    ("Toc toc", "¿Quién es?", "Lechuga", "Lechuga quién?", "Lechuga adentro que está lloviendo!"),
    ("Toc toc", "¿Quién es?", "Amapola", "Amapola quién?", "Amapola el que le toque!"),
    ("Toc toc", "¿Quién es?", "Boo", "Boo quién?", "No llores, es solo una broma!")
]

