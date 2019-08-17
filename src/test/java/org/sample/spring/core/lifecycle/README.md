#  Customizing the Nature of a Bean

## Types of bean life cycle events
Spring bean factory is responsible for managing the life cycle of beans created through spring container. The life cycle of beans consist of  **call back methods**  which can be categorized broadly in two groups:

-   **Post initialization**  call back methods
-   **Pre destruction**  call back methods

#### Spring bean life cycle diagram
![alt text]([https://cdn1.howtodoinjava.com/wp-content/uploads/Spring-bean-life-cycle.png](https://cdn1.howtodoinjava.com/wp-content/uploads/Spring-bean-life-cycle.png))
 ## Spring Bean Life Cycle Callback Methods
 Spring framework provides following  **4 ways for controlling life cycle events**  of a bean:

:point_right:  `InitializingBean`  and  `DisposableBean`  callback interfaces
:point_right:  *Aware interfaces for specific behavior
:point_right:  Custom  `init()`  and  `destroy()`  methods in bean configuration file
:point_right:  JSR-250 i.e.`@PostConstruct`  and  `@PreDestroy`  annotations

Multiple lifecycle mechanisms configured for the same bean, with different initialization methods, are called as follows:

1.  Methods annotated with  `@PostConstruct`
2.  `afterPropertiesSet()`  as defined by the  `InitializingBean`  callback interface    
3.  A custom configured  `init()`  method

Destroy methods are called in the same order:

5.  Methods annotated with  `@PreDestroy`
6.  `destroy()`  as defined by the  `DisposableBean`  callback interface
7.  A custom configured  `destroy()`  method