| Travis CI                                                                                                                                     | Codecov                                                                                                                                              |
|-----------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------|
| [![Build Status](https://travis-ci.org/ardeleanasm/quantum_computing.svg?branch=master)](https://travis-ci.org/ardeleanasm/quantum_computing) | [![codecov](https://codecov.io/gh/ardeleanasm/quantum_computing/branch/master/graph/badge.svg)](https://codecov.io/gh/ardeleanasm/quantum_computing) |

# Quantum Computing

This project contains libraries written in Java for simulating quantum algorithms. I created this libraries because I saw that there is a lack of libraries for Java that simulate quantum algorithms and the already existing Java based simulators don't expose the API for using it in new projects.

## Getting Started

You can get a stable version of this project by checking the releases and downloading a zip archive. If you want a development version, you can download the project as a zip or clone it:

```bash
git clone https://github.com/23ars/quantum_computing.git
```

### Prerequisities

For using this project you'll need a JDK ( Java Development Kit) and Apache Maven.


### Installing

1. Run `mvn install` in **complexnumber** directory.
2. Run `mvn install` in **quantum** directory
3. Run `mvn package` in **quantumapp** directory.

Note: If you want to use Eclipse as an IDE you need to perform an extra step in each directory:
`mvn eclipse:eclipse`

## Running the tests


### Complexnumber Junit Tests

The Junit Tests that are in **complexnumber** project verify if operations with complex numbers are performed correctly and give the 
correct results. For running this tests you will have to run `mvn test` in **complexnumber** directory.

```java
	@Test
	public void testConjugate() {
		ComplexNumber expectedNumber = new ComplexNumber(REAL_VALUE_FIRST_NO, -IMAGINARY_VALUE_FIRST_NO);
		ComplexNumber realNumber = null;
		realNumber = ComplexMath.conjugate(firstNumber);
		assertEquals(expectedNumber, realNumber);
	}
```

### QuantumLib Junit Tests

The Junit Tests from **quantum** project verify if operations on qubits are performed correctly. To run the tests, you will have to run `mvn test` in **quantum** directory.

```java
@RunWith(Suite.class)
@SuiteClasses({
	QuantumGatesTest.class,
	QuantumOperationsTest.class,
	QubitTest.class,
	QRegisterTest.class,
	QRegistersTest.class
})
public class AllTests {
	
}
```

## Deployment

To install the libraries is enough to run `mvn install`. If you want to deploy them, you will have to run `mvn deploy` in each folder.

## Built With

* Apache Maven 3.3.9

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/23ars/quantum_computing/tags). 

## Authors

* **Mihai Seba** - *Initial work* - [23ars](https://github.com/23ars)

See also the list of [contributors](https://github.com/23ars/quantum_computing/contributors) who participated in this project.

## License

This project is licensed under the GPL-3.0 License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments


