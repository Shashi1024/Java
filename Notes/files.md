*Topics --> I/O, Files, . . .*


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

- **Common Methods** --> 