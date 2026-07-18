- What is a Variable?
  - A variable is a named memory location that stores a value.
  - Instead of remembering the memory address, Java lets us use a meaningful name.

- Variable Declaration
  - Every variable has three parts:
  - DataType VariableName = Value;
  - int age = 30;

- Variable Naming Rules
  - studentAge
  - student_age
  - studentAge1
  - $salary
  - _name

- Primitive Data Types
  - There are 8 primitive data types in Java.
    
    | Data Type | Size | Example | Description |
    |-----------|------|---------|-------------|
    | `byte` | 1 byte | `100` | Stores small integer values. Range: -128 to 127 |
    | `short` | 2 bytes | `30000` | Stores medium-sized integer values. |
    | `int` | 4 bytes | `100000` | Most commonly used integer type. |
    | `long` | 8 bytes | `1000000000L` | Stores very large integer values. Suffix `L` is recommended. |
    | `float` | 4 bytes | `10.5f` | Single-precision floating-point number. Suffix `f` is required. |
    | `double` | 8 bytes | `10.5` | Double-precision floating-point number. Default choice for decimal values. |
    | `char` | 2 bytes | `'A'` | Stores a single Unicode character using single quotes. |
    | `boolean` | JVM-dependent | `true` | Stores logical values: `true` or `false`. |

- Non-Primitive Data Types
  - These are objects.
  - Examples:
    - String 
    - Array 
    - Class 
    - Interface 
    - Enum
- Wrapper Classes
  - Every primitive has an object equivalent.
    
    | Primitive | Wrapper |
    |-----------|---------|
    | `byte` | `Byte` |
    | `short` | `Short` |
    | `int` | `Integer` |
    | `long` | `Long` |
    | `float` | `Float` |
    | `double` | `Double` |
    | `char` | `Character` |
    | `boolean` | `Boolean` |

- Primitive vs Non-Primitive Data Types
    
    | Feature | Primitive | Non-Primitive |
    |---------|-----------|---------------|
    | Stores | Actual value | Reference to an object |
    | Memory | Stack (typically) | Reference on stack, object on heap |
    | Size | Fixed | Depends on the object |
    | Methods | ❌ No | ✅ Yes |
    | Default Value | `0`, `false`, etc. | `null` |
    | Examples | `int`, `double`, `char` | `String`, Array, Class, Interface |

- Type Casting
    
    | Type | Description | Automatic | Data Loss |
    |------|-------------|-----------|-----------|
    | Widening | Smaller → Larger | ✅ Yes | ❌ No |
    | Narrowing | Larger → Smaller | ❌ No | ✅ Possible |