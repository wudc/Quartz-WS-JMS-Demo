package com.cjet.demo;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.MappedTypes;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.cjet.demo.configuration.MyBatisConfiguration;
import com.cjet.demo.mapper.PaymentsMapper;
import com.cjet.demo.model.Payments;
import com.cjet.demo.model.PaymentsExample;
import com.cjet.demo.model.PaymentsExample.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyBatisConfiguration.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class PaymentsMapperTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Autowired
	PaymentsMapper paymentsMapper;

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

//	@Autowired
//	private SqlSession sqlSession;
//
//	@Autowired
//	private ApplicationContext applicationContext;
//
//	@Test
//	public void testAutoConfigureComponents() {
//		// @AutoConfigureMybatis
//		this.applicationContext.getBean(JdbcTemplate.class);
//		this.applicationContext.getBean(NamedParameterJdbcTemplate.class);
//		this.applicationContext.getBean(DataSourceTransactionManager.class);
//		this.applicationContext.getBean(TransactionInterceptor.class);
//		// @AutoConfigureCache
////	    this.applicationContext.getBean(CacheManager.class);
////	    this.applicationContext.getBean(CacheInterceptor.class);
//	}

//	@Test
//	public void didNotInjectExampleComponent() {
//		this.thrown.expect(NoSuchBeanDefinitionException.class);
//		this.applicationContext.getBean(Payments.class);
//		this.applicationContext.getBean(PaymentsExample.class);
//		this.applicationContext.getBean(PaymentsMapper.class);		
//	}

	@Test
	public void testSqlSession() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", 1);
		parameters.put("amount", 20.0);
		parameters.put("status", "Accepted");
		parameters.put("method", "Cash");
//		sqlSession.insert("Payments", parameters);
//		Payments findPayment = sqlSession.selectOne("savePayment", 1L);
//		assertThat(findPayment.getId()).isNotNull().isEqualTo(1L);
//		assertThat(findPayment.getMethod()).isNotNull().isEqualTo("Cash");
	}

	@Test
	public void shouldGetAPaymentByExample() {
		PaymentsExample example = new PaymentsExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(1);
		List<Payments>  payments = paymentsMapper.selectByExample(example);

//		Payments payments = paymentsMapper.selectByPrimaryKey(1);
//		assertThat(payments).isNotNull();
//		assertThat(payments.getId()).isEqualTo(1L);
//        assertThat(payments.getAmount()).isEqualTo(25.0);
//        assertThat(payments.getMethod()).isEqualTo("Cash");

	}
	
	@Test
	public void shouldGetAPaymentByPrimaryKey() {

		Payments payments = paymentsMapper.selectByPrimaryKey(1);
//		assertThat(payments).isNotNull();
//		assertThat(payments.getId()).isEqualTo(1L);
//        assertThat(payments.getAmount()).isEqualTo(25.0);
//        assertThat(payments.getMethod()).isEqualTo("Cash");

	}	

	@Test
	public void shouldGetAllPayments() {
		PaymentsExample example = new PaymentsExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(1);
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			PaymentsMapper mapper = sqlSession.getMapper(PaymentsMapper.class);
			List<Payments> payments = mapper.selectByExample(null);
			Assertions.assertNotNull(payments);
		} 
	}
}
