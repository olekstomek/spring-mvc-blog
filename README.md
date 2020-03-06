I finished [course assumptions](https://nakov.com/blog/2016/08/05/creating-a-blog-system-with-spring-mvc-thymeleaf-jpa-and-mysql/). Here they are found
all the details. This is my solution suggestion. The solutions relate to the assumptions that appeared in the tutorial. My goal is to best reproduce the logic and appearance described page. Running application automatically creates a database (by default, login details are set to root/root for MySQL) and creates tables with sample data.
The source table has MD5 or MD4 hash data for password, in my implementation I used Bcrypt because the previous ones are old. To log in to the site you can use the login details tomasz/tomasz by default. You can also create your user and log in to him.
This is a demo of action:
![](blog-mvc.gif)

As I mentioned, the assumptions in the entry are implemented, if you are looking for training, you can make a pull request with changes that implement new ones
functionalities or improve existing ones. A few suggestions from me that can be easily implemented: e.g. implementation of lombok, writing unit tests,
Java/libraries version upgrade, pagination implementation for table data, search engine implementation, MD4/MD5 implementation instead of currently used Bcrypt, change database, implementation of stub data instead of database, frontend improvement (e.g. bootstrap), and much more, have fun. 😁

I inferred some of the solutions from screenshots if the description did not indicate a specific solution.