# wpdistance

A RESTful API for AWS Lambda which returns the nearest office locations of a major payment processing company.

## Usage

The API can be tested at [https://c5t0egwn4a.execute-api.eu-west-2.amazonaws.com/wpdistance](https://c5t0egwn4a.execute-api.eu-west-2.amazonaws.com/wpdistance)

Arguments:
* Latitude - The latitude of the query position
* Longitude - The longitude of the query position
* numberOfResults (optional) - The maximum number of results to return

Example:
[https://c5t0egwn4a.execute-api.eu-west-2.amazonaws.com/wpdistance?latitude=52.2&longitude=0.1&numberOfResults=10](https://c5t0egwn4a.execute-api.eu-west-2.amazonaws.com/wpdistance?latitude=52.2&longitude=0.1&numberOfResults=10)

## Built With

* [AWS Toolkit for Eclipse](https://github.com/aws/aws-toolkit-eclipse/) 

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file.
