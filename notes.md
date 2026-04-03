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

  ## Java Imports — What they mean

Think of imports like "calling your team members before starting work"
Without importing, Java doesn't know where to find these tools.

### import java.net.http.HttpClient
- `java.net`        = Java's networking package (anything internet related)
- `.http`           = specifically the HTTP part of networking
- `.HttpClient`     = the actual tool — the POSTMAN who sends your request
- Without this: Java doesn't know what HttpClient means

### import java.net.http.HttpRequest
- Same package as HttpClient (java.net.http)
- `.HttpRequest`    = the LETTER you're sending (contains URL, headers, body)
- Without this: Java doesn't know how to build a request

### import java.net.http.HttpResponse
- Same package again
- `.HttpResponse`   = the REPLY that comes back from Sarvam's server
- Contains: status code, headers, and the actual response body
- Without this: Java doesn't know how to read the response

### import java.net.URI
- `java.net`        = Java's networking package
- `.URI`            = Uniform Resource Identifier (fancy name for a URL)
- Used to create the destination address for your request
- Example: URI.create("https://api.sarvam.ai/translate")
- Without this: Java doesn't know how to handle URLs

## The Pattern — Why same package (java.net.http)?
- HttpClient, HttpRequest, HttpResponse all live in the same package
- Because they all work TOGETHER to make one HTTP call
- Client SENDS the Request and receives the Response
- Think of them as a team:
    HttpClient   = the postman
    HttpRequest  = the letter
    HttpResponse = the reply

## Golden Rule about imports
- Java only loads what you ask for — nothing extra
- Every class you use must be imported (except java.lang — that's automatic)
- java.lang includes: String, System, Math — that's why you never import those

## Java Text Blocks (triple quotes)
- Introduced in Java 13+
- Used for writing multi-line strings cleanly (like JSON)
- RULE: opening """ must have nothing after it on the same line
- RULE: content starts on the NEXT line
- RULE: closing """ can be on its own line

- Wrong:  String x = """{ "key": "value" }""";
- Right:  String x =

                  """
                  
                  { "key": "value" }
  
                  """;

## JSON Parsing — Reading Sarvam's Response

### What Sarvam sends back (raw):
{"request_id":"abc123","translated_text":"नमस्ते, मेरा नाम वाणी है!","source_language_code":"en-IN"}

### Problem: this is just one big String — Java can't use it directly
### Solution: parse it into a JSONObject so we can pick out specific fields

---

### Line by line:

// org.json.JSONObject jsonResponse = new org.json.JSONObject(response.body());
- response.body()     = the raw string Sarvam sent back
- new JSONObject(...)  = converts that raw string into a structured object
- jsonResponse         = now we can access individual fields by name
- Think of it like: converting a letter into a filing cabinet with labeled drawers

// String translatedText = jsonResponse.getString("translated_text");
- .getString("translated_text") = open the drawer labeled "translated_text"
- Returns the value inside that drawer as a String
- Other methods you'll use:
    .getInt("number")       = get a number value
    .getBoolean("success")  = get a true/false value
    .getJSONObject("data")  = get a nested JSON object

---

## File Writing — Saving output to a file

// java.nio.file.Files.writeString(path, content, charset)
- Files          = Java's built-in file handling class
- .writeString() = writes a String directly to a file
- Three arguments:
    1. Path.of("translation_output.txt") = WHERE to save (filename)
    2. "Original: ..." + translatedText  = WHAT to save (the content)
    3. StandardCharsets.UTF_8            = HOW to save (UTF-8 supports Hindi/all languages)

// Why UTF-8?
- UTF-8 is a character encoding standard
- Supports ALL languages including Hindi, Bengali, Tamil etc.
- Without UTF-8 — Hindi characters show as ????? or garbage
- Always use UTF-8 when working with Indian languages

---

## The full flow of our program now:

Step 1: Create HttpClient         (the postman)
Step 2: Prepare JSON body         (write the letter)
Step 3: Build HttpRequest         (address + seal the envelope)
Step 4: Send + get HttpResponse   (postman delivers, brings reply)
Step 5: Parse JSON response       (open reply, find translated_text drawer)
Step 6: Write to file             (save Hindi text properly with UTF-8)
