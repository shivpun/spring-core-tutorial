# Bean Definition Inheritance
Spring Bean definition inheritance has nothing to do with Java class inheritance but the inheritance concept is same.

You can define a parent bean definition as a template and other child beans can inherit the required configuration from the parent bean.

  For Example:-

	<bean id="common" abstract="true">
		<property name="name" value="Product" />
		<property name="code" value="ABC" />
	</bean>

	<bean id="foo" parent="common" class="org.sample.spring.core.bean.inheritance.Foo"/>	
	
	OR
	
	<bean id="fooInherit" class="org.sample.spring.core.bean.inheritance.Foo" abstract="true" />

	<bean id="f1" parent="fooInherit">
		<property name="name" value="Sale Order" />
		<property name="code" value="PQR" />
	</bean>

	<bean id="f2" parent="fooInherit">
		<property name="name" value="Purchase Order" />
		<property name="code" value="ABC" />
	</bean>

	public class Foo {

		private static final Logger logger = LoggerFactory.getLogger(Foo.class);

		private String name;

		private String code;

		public String getName() {
			logger.info(String.format("Method [%s] return [%s]", "getName", this.name));
			return name;
		}

		public void setName(String name) {
			logger.info(String.format("Method [%s] set [%s]", "setName", name));
			this.name = name;
		}

		public String getCode() {
			logger.info(String.format("Method [%s] return [%s]", "getCode", this.code));
			return code;
		}

		public void setCode(String code) {
			logger.info(String.format("Method [%s] return [%s]", "setCode", code));
			this.code = code;
		}

		@Override
		public String toString() {
			return "Foo [name=" + name + ", code=" + code +"]";
		}
	}

		