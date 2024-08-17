# citydistance

A Java REST API written for [AWS Lambda](https://aws.amazon.com/lambda/), which currently returns the cities nearest to a specified GPS location. It can be adapted as necessary to use a different location data set to serve a different purpose.

[AWS Lambda](https://aws.amazon.com/lambda/) provides serverless computing. I package the code here into a JAR file which I upload to Lambda. It is invoked through [AWS API Gateway](https://aws.amazon.com/api-gateway/). AWS calls the LambdaFunctionHandler class in [LambdaFunctionHandler.java](CityDistance/src/main/java/joeldockray/demos/awslambda/citydistance/LambdaFunctionHandler.java). The distance calculation is currently performed in [Location.java](CityDistance/src/main/java/joeldockray/demos/awslambda/citydistance/Location.java#L44) using the two-argument arctangent formula from the Wikipedia [great circle distance](https://en.wikipedia.org/wiki/Great-circle_distance#Computational_formulae) article. The city data (see below) is in a [CSV file](CityDistance/src/main/resources/Cities.csv) loaded from a resources folder. There are also many [unit tests](CityDistance/src/test/java/joeldockray/demos/awslambda/citydistance) that use JUnit 5.

## Usage

The API can be tested at [https://hjkbhjet48.execute-api.eu-west-2.amazonaws.com/production/citydistance](https://hjkbhjet48.execute-api.eu-west-2.amazonaws.com/production/citydistance)

Arguments:
* Latitude - The latitude of the query position
* Longitude - The longitude of the query position
* numberOfResults (optional) - The maximum number of results to return

Example for Cambridge, UK:
[https://hjkbhjet48.execute-api.eu-west-2.amazonaws.com/production/citydistance?latitude=52.2&longitude=0.1&numberOfResults=10](https://hjkbhjet48.execute-api.eu-west-2.amazonaws.com/production/citydistance?latitude=52.2&longitude=0.1&numberOfResults=10)

## Data Source and Accuracy

This API was created for demonstration purposes and its results should not be used for anything important. It uses [city location data](CityDistance/src/main/resources/Cities.csv) from [Wikidata](https://www.wikidata.org/wiki/Wikidata:Main_Page) that are open and [not subject to copyright](https://www.wikidata.org/wiki/Wikidata:Data_donation#Wikidata_and_copyright), obtained via the [Wikidata Query Service](https://query.wikidata.org/). No warranty is given as to the accuracy of the data nor the distances returned by this API.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file.

