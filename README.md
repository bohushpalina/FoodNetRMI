# FoodNetRMI

A distributed client-server food ordering application implemented in Java using Remote Method Invocation (Java RMI).

---

## Features

* **Remote Service Invocation:** Communicates via Java RMI Registry listening on port 1099.
* **Dynamic Menu Retrieval:** Fetches available menu items dynamically from the server.
* **Remote Price Calculation:** Calculates total order costs on the server side.
* **Order Processing:** Submits custom orders with delivery address details using serializable objects.

---

## Project Structure

### `foodnet.client`

* `FoodClientRMI.java` — Client application for user interaction, menu inspection, and order placement.

### `foodnet.server`

* `FoodServer.java` — RMI server implementation establishing the registry and processing order requests.

### `foodnet.rmi`

* `FoodService.java` — Remote interface defining remote methods (`getMenu`, `calculateSum`, `submitOrder`).

### `foodnet.data`

* `Dish.java` — Serializable model representing dish items and pricing.
* `Order.java` — Serializable model encapsulating ordered dishes and delivery address.

---

## Tech Stack

* **Language:** Java 17+
* **Architecture:** Client-Server Distributed System
* **Technologies:** Java RMI (Remote Method Invocation), Object Serialization (`java.io.Serializable`)

---

## Getting Started

### Prerequisites

Ensure JDK 17+ is installed on your system.

### Running the Application

1. **Clone the repository:**

   ```bash
   git clone https://github.com/bohushpalina/FoodNetRMI.git
   cd FoodNetRMI
   ```

2. **Compile Java source files:**

   ```bash
   javac -d bin src/foodnet/data/*.java src/foodnet/rmi/*.java src/foodnet/server/*.java src/foodnet/client/*.java
   ```

3. **Start the RMI Server:**

   ```bash
   java -cp bin foodnet.server.FoodServer
   ```

4. **Run the Client:**

   ```bash
   java -cp bin foodnet.client.FoodClientRMI
   ```

---

## Author

**Palina Bohush**
