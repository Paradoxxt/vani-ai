# Vani AI — My Learning Notes

## What is an API?
- Like a waiter in a restaurant
- You (Java code) → Waiter (API) → Kitchen (Sarvam servers)
- You send a REQUEST, you get back a RESPONSE
- You never see what happens inside the kitchen

## What is JSON?
- A way to send data between your app and an API
- Like a structured letter format
- Example of what we SEND to Sarvam:
  {
      "input": "What is the weather?",
      "language_code": "hi-IN"
  }
- Example of what Sarvam sends BACK:
  {
      "status": "success",
      "response": "Aaj mausam achha hai"
  }

## HTTP Request — Breaking it down

### The "sending a letter" analogy:
- HttpClient        = the postman (carries your letter)
- URI               = destination address (Sarvam's server URL)
- header Content-Type = envelope label (tells Sarvam "I'm sending JSON")
- header api-key    = your ID card (proves you're allowed to use the API)
- POST              = you are SENDING data to the server
- GET               = you are just FETCHING data, sending nothing
- .build()          = seal the envelope (request is ready but NOT sent yet)
- client.send()     = actually hand the letter to the postman (sends it NOW)

### The code pattern — every API call follows this:
  Step 1 — Create the client
  HttpClient client = HttpClient.newHttpClient();

  Step 2 — Prepare your JSON message
  String json = "{ your data here }";

  Step 3 — Build the request
  HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create("API URL here"))
      .header("Content-Type", "application/json")
      .header("api-subscription-key", "YOUR KEY")
      .POST(HttpRequest.BodyPublishers.ofString(json))
      .build();

  Step 4 — Send it and get response
  HttpResponse<String> response = client.send(request,
      HttpResponse.BodyHandlers.ofString());

  Step 5 — Read the response
  System.out.println(response.body());

## OOP Concepts (from VaniCommand example)

### Terms I now know:
- Fields            = variables declared at top of class (commandText, language)
- Constructor       = special method with same name as class, runs when object is created
- Parameters        = values passed INTO a method or constructor
- Object/Instance   = created using "new" keyword
- Reference variable = the name you give your object (like "cmd")
- this.variable     = refers to the CLASS field, not the parameter

### Example:
  VaniCommand cmd = new VaniCommand("Play music", "Hindi");
  //          ^^^                    ^^^^^^^^^^^   ^^^^^
  //    reference var               parameter1    parameter2

## Why Raw Java BEFORE Spring Boot?
- Spring Boot hides 100s of steps automatically
- Without knowing raw Java, you can't debug Spring Boot errors
- Learning order: Raw HttpClient → Retrofit (Android) → Spring Boot
- My satellite project failed because I did this in REVERSE order

## Frameworks vs Raw Code
- Raw HttpClient  = manual, verbose, but you understand everything
- Retrofit        = Android industry standard, 20 lines becomes 3 lines
- Spring Boot     = Backend industry standard, handles everything automatically
- Rule: Always understand raw first, then use the framework

## Sarvam AI — What it gives us for Vani AI
- STT (Speech to Text) = converts voice → text, supports 22 Indian languages
- Sarvam-M             = AI brain, understands intent, replies intelligently
- Bulbul TTS           = converts text → natural voice, 11 Indian languages
- All accessed via REST API (HTTP requests from Java)

## Vani AI Architecture
  User speaks
      ↓
  Sarvam STT (voice → text)
      ↓
  Sarvam-M (understand intent, generate response)
      ↓
  Sarvam Bulbul TTS (text → voice)
      ↓
  Bluetooth speaker output

## Vani AI Build Phases
  Phase 1 (Week 1-2)  — Basic voice in + AI response + voice out (Python logic in Java)
  Phase 2 (Week 3-4)  — Bluetooth speaker integration + wake word "Hey Vani"
  Phase 3 (Week 5-7)  — Real commands (weather, news, timer) + all Indian languages
  Phase 4 (Week 8-10) — Proper Android app in Java/Kotlin + Raspberry Pi port
  Phase 5 (Week 11-12)— GitHub release + demo video + Sarvam startup program
