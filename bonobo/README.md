bonobo
======

This repository has multiple projects. The sole purpose of these projects are Learning java technologies.

1. Write a small project to show synchronization issues on Database level. It should use hibernate. Write Junits to validate. You could
use campaign update example for illustration.

2. There are some houses in row. A thief knows about the maximum wealth he can steal from each house. Theif can robber only one house every
night. If theif robs nth house then owner of that house inform n-1 and n+1 house owner about the robbery and put them on alert.
Thus theif can not rob n-1 and n+1 house. Implement a solution so that theif can rob maximum wealth.

3. We have a caching mechanism which stores computation result of a method say, Compute(number). If same number is computed again then result
is served from cache. But this approach is not effective enough in multuthreaded environment. Suppose three thread starts to compute same
number which is not already in cache then compute will be executed three times. Redesign caching to solve this problem.

4. Design a class which is singleton and should be work properly in multithreaded environment. This class contains a method String[] getDatabaseConfiguration(). This method is called to get database configuration. We need to design a cache for database configuration so that a client never gets a data more than 5 minutes old. For this you need to define an another method getCachedDatabaseConfiguration() which would call getDatabaseConfiguration() internally to refresh cache.
