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

- Step 1: Create HttpClient         (the postman)
- Step 2: Prepare JSON body         (write the letter)
- Step 3: Build HttpRequest         (address + seal the envelope)
- Step 4: Send + get HttpResponse   (postman delivers, brings reply)
- Step 5: Parse JSON response       (open reply, find translated_text drawer)
- Step 6: Write to file             (save Hindi text properly with UTF-8)

## Audio Recording in Java — How it works

### Why do we need a dependency for audio?
- Java has basic audio support built-in (javax.sound.sampled)
- But it has limitations with certain audio formats
- tritonus-share fills the gaps Java's built-in audio misses
- Think of it like: Java gives you a basic microphone,
  tritonus gives you a professional one

### What is tritonus-share?
- A library that extends Java's audio capabilities
- Helps Java properly handle audio formats like WAV
- WAV = the audio format Sarvam STT API accepts
- Without it: Java might record in wrong format
- With it: clean, compatible audio recording

### Audio Recording Flow in Vani AI:
Step 1 — Open microphone (AudioSystem.getTargetDataLine)
Step 2 — Start recording (line.start())
Step 3 — Capture audio bytes while user speaks
Step 4 — Stop recording (line.stop())
Step 5 — Save as WAV file
Step 6 — Send WAV file to Sarvam STT API
Step 7 — Get back text of what was said

### Key Java Audio Classes we'll use:
- AudioSystem        = the main door to Java's audio system
- TargetDataLine     = represents the microphone input line
- AudioFormat        = describes how audio is recorded
                       (sample rate, bit depth, channels)
- AudioInputStream   = stream of audio data from microphone
- AudioFileWriter    = writes audio data to a WAV file

### AudioFormat — what the settings mean:
AudioFormat format = new AudioFormat(
    16000,    // Sample rate — 16000 Hz (good quality for speech)
    16,       // Bit depth — 16 bit (standard)
    1,        // Channels — 1 = mono (one microphone)
    true,     // Signed — true (standard)
    false     // Big endian — false (standard for WAV)
);
- Sample rate: how many times per second audio is sampled
  Higher = better quality but bigger file
  16000 Hz is perfect for voice/speech
- Bit depth: how much detail per sample
  16 bit = CD quality for voice
- Channels: 1 = mono (one mic), 2 = stereo (two mics)
  We use mono since it's just one voice

### Why WAV format?
- WAV = Waveform Audio File Format
- Uncompressed audio — no quality loss
- Sarvam STT API accepts WAV
- Easy for Java to write and read

## VoiceRecorder.java — Full Breakdown

### Constants (settings at the top)
private static final int SAMPLE_RATE = 16000;
private static final int SAMPLE_SIZE = 16;
private static final int CHANNELS = 1;
private static final int RECORD_SECONDS = 5;

- static final = constant — never changes during program run
- SAMPLE_RATE 16000  = capture 16000 audio samples per second
                       perfect for speech, what Sarvam expects
- SAMPLE_SIZE 16     = each sample is 16 bits = CD quality voice
- CHANNELS 1         = mono (one microphone, one voice)
- RECORD_SECONDS 5   = record for 5 seconds then stop

---

### Step 1 — AudioFormat
AudioFormat format = new AudioFormat(
    SAMPLE_RATE,  // 16000 Hz
    SAMPLE_SIZE,  // 16 bit
    CHANNELS,     // 1 = mono
    true,         // signed (standard for PCM audio)
    false         // little endian (standard for WAV on Windows)
);

- AudioFormat = a blueprint describing HOW to record audio
- Like telling the microphone: "record at this quality"
- Sarvam STT API requires exactly these settings
- Little endian = how bytes are ordered in memory
  Windows uses little endian, Mac uses big endian

---

### Step 2 — Open Microphone
DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

- DataLine.Info = describes what kind of audio line we need
- TargetDataLine.class = "I want an INPUT line" (microphone)
  (SourceDataLine would be OUTPUT = speakers)
- Think of it like: "I need a microphone that records in THIS format"

if (!AudioSystem.isLineSupported(info)) {
    throw new Exception("Microphone not supported!");
}
- Safety check — does this computer have a compatible mic?
- If no mic found → throw exception (stop program with error)
- Always check before trying to use hardware!

TargetDataLine microphone = (TargetDataLine) AudioSystem.getLine(info);
microphone.open(format);
- AudioSystem.getLine(info) = get the actual microphone
- .open(format) = turn on the microphone, prepare it to record
- Like plugging in and turning on your mic

---

### Step 3 — Start Recording
microphone.start();
- Actually starts capturing audio from the microphone
- Audio data starts flowing into memory
- Think of it like: pressing the record button

AudioInputStream audioStream = new AudioInputStream(microphone);
- Wraps the microphone in a stream
- Stream = continuous flow of audio data
- Like a water pipe — data flows continuously

File audioFile = new File("voice_input.wav");
- Creates a File object pointing to voice_input.wav
- File doesn't exist yet — we're just preparing the path
- Like choosing where to save before actually saving

---

### Step 4 — Record in Separate Thread
Thread recordThread = new Thread(() -> {
    AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, audioFile);
});
recordThread.start();

- Thread = a separate worker running alongside main program
- Why separate thread? Because writing audio is BLOCKING
  (it would freeze the main program while recording)
- With separate thread: main program continues counting 5 seconds
  while recording thread captures audio simultaneously
- AudioFileFormat.Type.WAVE = save as WAV format
- Think of it like: hiring an assistant to record
  while you watch the clock

---

### Step 5 — Stop After 5 Seconds
Thread.sleep(RECORD_SECONDS * 1000);
- Thread.sleep() = pause the main program
- 5 * 1000 = 5000 milliseconds = 5 seconds
- Why milliseconds? Java measures time in milliseconds
- During this sleep, the recording thread is busy capturing

microphone.stop();
microphone.close();
- .stop() = stop capturing audio
- .close() = release the microphone (free the hardware)
- ALWAYS close hardware after using it!
- Like turning off and unplugging the microphone

---

### Full Flow Summary:
1. Define audio settings (format)
2. Check if mic exists
3. Open and turn on mic
4. Start recording thread (runs in background)
5. Main thread sleeps 5 seconds
6. Stop mic and close it
7. Return the WAV file

### What is a Thread?
- Your program normally runs one task at a time (single thread)
- With multiple threads = doing two things simultaneously
- Example: 
  Main thread  → counting 5 seconds
  Record thread → capturing audio
  Both happening at the SAME TIME
- This is called multithreading — very important concept!

## App.java v2 — Voice Input Breakdown

### New imports added:
import java.io.File;
- File = represents a file on your computer
- We use it to hold the recorded voice_input.wav

import java.nio.file.Files;
- Files = utility class for file operations
- We use Files.readAllBytes() to read WAV file into memory

### What changed from v1 to v2:

V1 (yesterday):
- Hardcoded text → Sarvam Translate API → Hindi text

V2 (today):
- Microphone → WAV file → Sarvam STT API → text of what you said

---

### Step 1 — Record voice
File audioFile = VoiceRecorder.recordAudio();
- Calls our VoiceRecorder class we just made
- Records 5 seconds from microphone
- Returns the saved voice_input.wav file
- audioFile = a pointer to that WAV file on disk

---

### Step 2 — Read audio as bytes
byte[] audioBytes = Files.readAllBytes(audioFile.toPath());

- byte[] = array of raw bytes (0s and 1s)
- Files.readAllBytes() = reads entire file into memory as bytes
- audioFile.toPath() = converts File to Path (needed by Files class)
- Why bytes? Because APIs transfer raw binary data over internet
- Think of it like: converting a physical letter into digital bits
  to send over email

---

### Step 3 — Send to Sarvam STT API

// New header:
.header("Content-Type", "audio/wav")
- Telling Sarvam: "I'm sending you a WAV audio file"
- Different from before where we sent "application/json"
- Content-Type tells the server what FORMAT the data is in

// New body:
.POST(HttpRequest.BodyPublishers.ofByteArray(audioBytes))
- ofByteArray() = sends raw bytes instead of a String
- Before we used ofString(json) for text
- Now we use ofByteArray(audioBytes) for audio
- The audio file travels over internet as raw bytes

---

### Step 4 — Print what Vani heard
System.out.println("Sarvam heard: " + response.body());
- Sarvam STT processes the audio
- Returns JSON with the transcribed text
- Example response:
  {"transcript": "hello my name is Vani"}

---

### Key Concept — Different Content-Types:
| What you send  | Content-Type        | Body method          |
|----------------|---------------------|----------------------|
| JSON text      | application/json    | ofString(json)       |
| Audio file     | audio/wav           | ofByteArray(bytes)   |
| Image file     | image/jpeg          | ofByteArray(bytes)   |
| Form data      | multipart/form-data | ofInputStream(...)   |

---

### Full V2 Flow:


-     Microphone (5 sec)
-            ↓
-     voice_input.wav (saved on disk)
-            ↓
-     Files.readAllBytes() (loaded into memory)
-            ↓
-     HttpRequest with audio/wav header (sent to Sarvam)
-            ↓
-     Sarvam STT processes audio
-            ↓
-     Returns transcript text
-            ↓
-     System.out.println shows what Vani heard


