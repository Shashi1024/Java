> *Topics --> I/O, Files, Streams, . . .*
> *Link to Codes --> [Codes](../codes/collections/)*



## I/O

- Java I/O is based on the concept of streams. 
- A stream is a sequence of data.

- **Input Stream** --> A stream that reads data from a source 
- **Output Stream** --> A stream that writes data to a destination 

- *Java I/O Streams are Categporized into two types*,
  - ***Byte Streams***
    - handles raw binary data (bytes)
    - used for reading/writing any type of data (images, vids, ...)
    - Base classes: `InputStream` (for input) and `OutputStream` (for output).

  - ***Character Streams***
    - handles character data (text)
    - automatically handles character encoding
    - Used primarily for reading/writing text files.
    - Base classes: `Reader` (for input) and `Writer` (for output).


### The `java.io.File` Class: Representing Files and Directories

- It represents the pathname of a file or directory. ( is not an I/O stream itself)
- *it can be used to*,
  - Create, delete, rename files or directories.
  - Check existence, read/write permissions, size, and last modified time.
  - List contents of a directory.

- **Constructors**
  - `File(String pathname)` --> Creates a new `File` instance from a pathname string.
  - `File(String parent, String child)` --> Creates a new `File` instance from a parent pathname string and a child pathname string.
  - `File(File parent, String child)` --> Creates a new `File` instance from a parent `File` object and a child pathname string.

- **Common Methods** --> `boolean exists()`, `boolean isFile()`, `boolean isDirectory()`, `boolean createNewFile()`, `boolean delete()`, `boolean mkdir()` / `boolean mkdirs()`, `String getName()`, `String getAbsolutePath()`, `long length()`, `String[] list()`, `File[] listFiles()`




### Byte Streams
- reading and writing raw Bytes
- suitable for any type of data

- ***`InputStream` (Abstract Base Class for Byte Input)***
  - it is the abstract superclass for all classes representing an input stream of bytes.
  - **Methods** --> `int read()`, `int read(byte[] b)`, `void close()`, . . .

- ***`OutputStream` (Abstract Base Class for Byte Output)***
  - it is the abstract superclass for all classes representing an output stream of bytes.
  - **Methods** --> `void write(int b)`, `void write(byte[] b)`, `void flush()`, `void close()`, . . .

- **Concrete Byte Stream Classes** --> `FileInputStream`, `FileOutputStream`, `BufferedInputStream`, `BufferedOutputStream`, . . .



### Character Streams (for Text data)
- designed for handling text data
- automatically manages character encoding conversions between the native character set (e.g., UTF-8, UTF-16) and the platform's default or specified encoding.

- ***`Reader` (Abstract Base Class for Character Input)***
  - it is the abstract superclass for all classes representing a stream of characters.
  - **Methods** --> `int read()`, `int read(char[] cbuf)`, `void close()`, . . .

- ***`Writer` (Abstract Base Class for Character Output)***
  - it is the abstract superclass for all classes representing a stream of characters to which characters can be written.
  - **Methods** --> `void write(int c)`, `void write(char[] cbuf)`, `void write(String str)`, `void flush()`, `void close()`, . . .

- **Concrete Character Stream Classes** --> `FileReader`, `FileWriter`, `BufferedReader`, `BufferedWriter`, `PrintWriter`, . . .



---


- `java.nio` (New I/O) for Modern File Operations (more modern, robust and efficient)(better performance for large scale I/O)
- `Path` Interface  --> Represents a path to a file or directory in the file system.(more abstract and powerful than `java.io.File`)
- `Files` Class
