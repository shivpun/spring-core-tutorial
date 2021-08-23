#  Customizing the Nature of a Bean

## Types of bean life cycle events
Spring bean factory is responsible for managing the life cycle of beans created through spring container. The life cycle of beans consist of  **call back methods**  which can be categorized broadly in two groups:

-   **Post initialization**  call back methods
-   **Pre destruction**  call back methods

#### Spring bean life cycle diagram
![alt text](https://cdn1.howtodoinjava.com/wp-content/uploads/Spring-bean-life-cycle.png)
 ## Spring Bean Life Cycle Callback Methods
 Spring framework provides following  **4 ways for controlling life cycle events**  of a bean:

:point_right:  `InitializingBean`  and  `DisposableBean`  callback interfaces. <br />
:point_right:  *Aware interfaces for specific behavior <br />
:point_right:  Custom  `init()`  and  `destroy()`  methods in bean configuration file <br />
:point_right:  JSR-250 i.e.`@PostConstruct`  and  `@PreDestroy`  annotations <br />

#### Combining Lifecycle Mechanisms

Multiple lifecycle mechanisms configured for the same bean, with different initialization methods, are called as follows:

1.  Methods annotated with  `@PostConstruct`<br />
2.  `afterPropertiesSet()`  as defined by the  `InitializingBean`  callback interface    <br />
3.  A custom configured  `init()`  method <br />

Destroy methods are called in the same order:

1.  Methods annotated with  `@PreDestroy`<br />
2.  `destroy()`  as defined by the  `DisposableBean`  callback interface <br />
3.  A custom configured  `destroy()`  method <br />


Bean factory implementations should support the standard bean lifecycle interfaces as far as possible. The full set of initialization methods and their standard order is:

1.   BeanNameAware's setBeanName <br />
2.   BeanClassLoaderAware's setBeanClassLoader <br />
3.   BeanFactoryAware's setBeanFactory <br />
4.   EnvironmentAware's setEnvironment <br />
5.   EmbeddedValueResolverAware's setEmbeddedValueResolver <br />
6.   ResourceLoaderAware's setResourceLoader (only applicable when running in an application context) <br />
7.   ApplicationEventPublisherAware's setApplicationEventPublisher (only applicable when running in an application context) <br />
8.   MessageSourceAware's setMessageSource (only applicable when running in an application context) <br />
9.   ApplicationContextAware's setApplicationContext (only applicable when running in an application context) <br />
10.  ServletContextAware's setServletContext (only applicable when running in a web application context) <br />
11.  postProcessBeforeInitialization methods of BeanPostProcessors <br />
12.  InitializingBean's afterPropertiesSet <br />
13.  a custom init-method definition <br />
14.  postProcessAfterInitialization methods of BeanPostProcessors <br />


On shutdown of a bean factory, the following lifecycle methods apply: <br />

1.   postProcessBeforeDestruction methods of DestructionAwareBeanPostProcessors <br />
2.   DisposableBean's destroy <br />
3.   a custom destroy-method definition <br />
