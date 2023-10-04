When creating a script for a test

A]Creating pages
1. For each test identify the number of pages in the application that are involved
2. Goto src/main/java/pages and check if the pages needed for your tests are already present and can be reused
3. Now that you know what pages need to be created from scratch ,copy the temp page and rename it to the page you need
4. Rename the constructor to the classname
5. Identify all the elements in the page ( Can use IDE to locate elements)
6. Create getter and setter methods for objects
7. Create methods that are page specific actions

B]Creating tests
1. Goto src/test/java
2.Copy the template test class and rename it to the test you need
3. Rename the class name, test name and start writing tests
4. User Assert to verify a test step
5. In case you need to take a screenshot for any specific test use line:
st.onDemandScreenshotReport();

Points to remember:
1. Class name will always be capital
2. package,method can start will small letters
3. Browser launch, login, and logout happens automatically , no need to include these in your tests

