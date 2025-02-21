<!-- Begin README in LTR mode -->
<div dir="ltr">

# B+ Tree Database Management System

---

## **📖 Subject: Overview**
This project implements a **B+ Tree-based database management system (DBMS)** to simulate real-world database operations such as indexing, searching, adding, and deleting records efficiently. By leveraging the **B+ Tree** data structure, the project optimizes these operations, showcasing the power of advanced indexing mechanisms in modern database systems.

The project also includes a **Graphical User Interface (GUI)** built with **JavaFX**, providing an intuitive and user-friendly way to interact with the database.

---

## **🎯 Objectives**
- Master the **B+ Tree data structure**, including its **self-balancing** and indexing features.
- Simulate core **database functionalities**, including table creation and index management.
- Enhance familiarity with database systems and concepts.
- Develop a practical understanding of **data organization** and efficient querying.

---

## **🚀 Features**

### **1. Core Features**
1. **B+ Tree Implementation**:
   - Complete implementation of the B+ Tree with **index nodes** and **leaf nodes**.
   - Efficient **insert**, **search**, and **delete** operations.
   - Self-balancing mechanisms to maintain optimal performance.

2. **Table Management**:
   - Create tables with custom attributes (e.g., column names and data types).
   - Add, update, and delete records in tables.

3. **Indexing**:
   - Use B+ Trees to build and manage indices for optimized searches.
   - Support for primary, unique, and non-unique indexes.

4. **Query Processing**:
   - Advanced query capabilities, leveraging B+ Tree indexing for **range-based** and **point-based queries**.

5. **Graphical User Interface**:
   - Developed with **JavaFX**, featuring:
     - **Table Management View**: Easily create and modify tables.
     - **Query View**: Execute queries with clear visual feedback.
   - Styled with custom CSS for a professional look and feel.

---

## **📂 Project Structure**

### **Modules**
1. **B+ Tree Implementation**:
   - `BPlusTree.java`: Core implementation of the B+ Tree.
   - `Node.java`, `IndexNode.java`, and `LeafNode.java`: Specialized classes for tree nodes.
   - Supports seamless insert, search, and delete operations with auto-balancing.

2. **Database Management**:
   - `Table.java`: Handles table schema, records, and related operations.
   - `MakeTable.java`: Provides methods to define and manage table attributes.

3. **Query Engine**:
   - `Query.java`: Implements search and range-based query processing.

4. **Custom Data Structures**:
   - `AbstractMap.java`, `UnsortedTableMap.java`: Implements custom map functionality to supplement indexing and data management.

5. **Graphical User Interface**:
   - **FXML Files**:
     - `Main Page.fxml`: Main view for user interaction.
     - `MakeTable.fxml`: Interface for creating tables.
     - `Query.fxml`: Interface for executing queries.
   - **CSS Files**:
     - `Alerts.css`, `TextFieldStyle.css`: Provide custom styling for the GUI.

---

## **🛠️ Getting Started**

### **Prerequisites**
To run this project, ensure you have:
- **Java Development Kit (JDK)** version 11 or higher.
- **Maven** for build and dependency management.

### **Setup Instructions**
1. Clone the repository:
   ```bash
   git clone https://github.com/Erfan-fn/Bplus-Tree-based-database.git
   cd "path"
   mvn clean install
   mvn javafx:run


