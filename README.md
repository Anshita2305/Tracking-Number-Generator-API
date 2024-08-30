# Tracking Number Generator

## Description
A RESTful API for generating unique tracking numbers for parcels. Designed to be scalable and efficient, handling high concurrency.

## Features
- Generate unique tracking numbers
- Handle high concurrency
- Scalable horizontally

## Setup Instructions
1. Clone the repository:
    ```bash
    git clone https://github.com/Anshita2305/tracking-number-generator.git
    ```
2. Navigate into the project directory:
    ```bash
    cd tracking-number-generator
    ```
3. Install dependencies:
    
    - For Maven:
      ```bash
      mvn install
      ```

## Running the Application
1. Start the application:
    - For Maven:
      ```bash
      mvn spring-boot:run
      ```

## Testing
1. Run tests:

    - For Maven:
      ```bash
      mvn test
      ```

## Deployment
- Follow the instructions for deploying to your preferred platform, such as AWS, Google App Engine, or Heroku.

## Usage
- **Endpoint**: `GET /next-tracking-number`
- **Query Parameters**:
    - `origin_country_id`: ISO 3166-1 alpha-2 format
    - `destination_country_id`: ISO 3166-1 alpha-2 format
    - `weight`: Weight in kilograms (e.g., "1.234")
    - `created_at`: Timestamp in RFC 3339 format
    - `customer_id`: UUID of the customer
    - `customer_name`: Name of the customer
    - `customer_slug`: Slug-case of the customer name
