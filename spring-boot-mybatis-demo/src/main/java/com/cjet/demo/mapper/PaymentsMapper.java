package com.cjet.demo.mapper;

import com.cjet.demo.model.Payments;
import com.cjet.demo.model.PaymentsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PaymentsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quartz.payments
     *
     * @mbg.generated Sun Nov 17 21:40:22 EST 2019
     */
    long countByExample(PaymentsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quartz.payments
     *
     * @mbg.generated Sun Nov 17 21:40:22 EST 2019
     */
    int deleteByExample(PaymentsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quartz.payments
     *
     * @mbg.generated Sun Nov 17 21:40:22 EST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quartz.payments
     *
     * @mbg.generated Sun Nov 17 21:40:22 EST 2019
     */
    int insert(Payments record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quartz.payments
     *
     * @mbg.generated Sun Nov 17 21:40:22 EST 2019
     */
    int insertSelective(Payments record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quartz.payments
     *
     * @mbg.generated Sun Nov 17 21:40:22 EST 2019
     */
    List<Payments> selectByExample(PaymentsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quartz.payments
     *
     * @mbg.generated Sun Nov 17 21:40:22 EST 2019
     */
    Payments selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quartz.payments
     *
     * @mbg.generated Sun Nov 17 21:40:22 EST 2019
     */
    int updateByExampleSelective(@Param("record") Payments record, @Param("example") PaymentsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quartz.payments
     *
     * @mbg.generated Sun Nov 17 21:40:22 EST 2019
     */
    int updateByExample(@Param("record") Payments record, @Param("example") PaymentsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quartz.payments
     *
     * @mbg.generated Sun Nov 17 21:40:22 EST 2019
     */
    int updateByPrimaryKeySelective(Payments record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table quartz.payments
     *
     * @mbg.generated Sun Nov 17 21:40:22 EST 2019
     */
    int updateByPrimaryKey(Payments record);
}