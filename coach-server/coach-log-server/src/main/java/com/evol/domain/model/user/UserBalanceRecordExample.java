package com.evol.domain.model.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserBalanceRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserBalanceRecordExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andHistoryBalanceMoneyIsNull() {
            addCriterion("history_balance_money is null");
            return (Criteria) this;
        }

        public Criteria andHistoryBalanceMoneyIsNotNull() {
            addCriterion("history_balance_money is not null");
            return (Criteria) this;
        }

        public Criteria andHistoryBalanceMoneyEqualTo(Integer value) {
            addCriterion("history_balance_money =", value, "historyBalanceMoney");
            return (Criteria) this;
        }

        public Criteria andHistoryBalanceMoneyNotEqualTo(Integer value) {
            addCriterion("history_balance_money <>", value, "historyBalanceMoney");
            return (Criteria) this;
        }

        public Criteria andHistoryBalanceMoneyGreaterThan(Integer value) {
            addCriterion("history_balance_money >", value, "historyBalanceMoney");
            return (Criteria) this;
        }

        public Criteria andHistoryBalanceMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("history_balance_money >=", value, "historyBalanceMoney");
            return (Criteria) this;
        }

        public Criteria andHistoryBalanceMoneyLessThan(Integer value) {
            addCriterion("history_balance_money <", value, "historyBalanceMoney");
            return (Criteria) this;
        }

        public Criteria andHistoryBalanceMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("history_balance_money <=", value, "historyBalanceMoney");
            return (Criteria) this;
        }

        public Criteria andHistoryBalanceMoneyIn(List<Integer> values) {
            addCriterion("history_balance_money in", values, "historyBalanceMoney");
            return (Criteria) this;
        }

        public Criteria andHistoryBalanceMoneyNotIn(List<Integer> values) {
            addCriterion("history_balance_money not in", values, "historyBalanceMoney");
            return (Criteria) this;
        }

        public Criteria andHistoryBalanceMoneyBetween(Integer value1, Integer value2) {
            addCriterion("history_balance_money between", value1, value2, "historyBalanceMoney");
            return (Criteria) this;
        }

        public Criteria andHistoryBalanceMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("history_balance_money not between", value1, value2, "historyBalanceMoney");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceMoneyIsNull() {
            addCriterion("current_balance_money is null");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceMoneyIsNotNull() {
            addCriterion("current_balance_money is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceMoneyEqualTo(Integer value) {
            addCriterion("current_balance_money =", value, "currentBalanceMoney");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceMoneyNotEqualTo(Integer value) {
            addCriterion("current_balance_money <>", value, "currentBalanceMoney");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceMoneyGreaterThan(Integer value) {
            addCriterion("current_balance_money >", value, "currentBalanceMoney");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("current_balance_money >=", value, "currentBalanceMoney");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceMoneyLessThan(Integer value) {
            addCriterion("current_balance_money <", value, "currentBalanceMoney");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("current_balance_money <=", value, "currentBalanceMoney");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceMoneyIn(List<Integer> values) {
            addCriterion("current_balance_money in", values, "currentBalanceMoney");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceMoneyNotIn(List<Integer> values) {
            addCriterion("current_balance_money not in", values, "currentBalanceMoney");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceMoneyBetween(Integer value1, Integer value2) {
            addCriterion("current_balance_money between", value1, value2, "currentBalanceMoney");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("current_balance_money not between", value1, value2, "currentBalanceMoney");
            return (Criteria) this;
        }

        public Criteria andChangeMoneyIsNull() {
            addCriterion("change_money is null");
            return (Criteria) this;
        }

        public Criteria andChangeMoneyIsNotNull() {
            addCriterion("change_money is not null");
            return (Criteria) this;
        }

        public Criteria andChangeMoneyEqualTo(Integer value) {
            addCriterion("change_money =", value, "changeMoney");
            return (Criteria) this;
        }

        public Criteria andChangeMoneyNotEqualTo(Integer value) {
            addCriterion("change_money <>", value, "changeMoney");
            return (Criteria) this;
        }

        public Criteria andChangeMoneyGreaterThan(Integer value) {
            addCriterion("change_money >", value, "changeMoney");
            return (Criteria) this;
        }

        public Criteria andChangeMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("change_money >=", value, "changeMoney");
            return (Criteria) this;
        }

        public Criteria andChangeMoneyLessThan(Integer value) {
            addCriterion("change_money <", value, "changeMoney");
            return (Criteria) this;
        }

        public Criteria andChangeMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("change_money <=", value, "changeMoney");
            return (Criteria) this;
        }

        public Criteria andChangeMoneyIn(List<Integer> values) {
            addCriterion("change_money in", values, "changeMoney");
            return (Criteria) this;
        }

        public Criteria andChangeMoneyNotIn(List<Integer> values) {
            addCriterion("change_money not in", values, "changeMoney");
            return (Criteria) this;
        }

        public Criteria andChangeMoneyBetween(Integer value1, Integer value2) {
            addCriterion("change_money between", value1, value2, "changeMoney");
            return (Criteria) this;
        }

        public Criteria andChangeMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("change_money not between", value1, value2, "changeMoney");
            return (Criteria) this;
        }

        public Criteria andMoneyInOutTypeIsNull() {
            addCriterion("money_in_out_type is null");
            return (Criteria) this;
        }

        public Criteria andMoneyInOutTypeIsNotNull() {
            addCriterion("money_in_out_type is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyInOutTypeEqualTo(Integer value) {
            addCriterion("money_in_out_type =", value, "moneyInOutType");
            return (Criteria) this;
        }

        public Criteria andMoneyInOutTypeNotEqualTo(Integer value) {
            addCriterion("money_in_out_type <>", value, "moneyInOutType");
            return (Criteria) this;
        }

        public Criteria andMoneyInOutTypeGreaterThan(Integer value) {
            addCriterion("money_in_out_type >", value, "moneyInOutType");
            return (Criteria) this;
        }

        public Criteria andMoneyInOutTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("money_in_out_type >=", value, "moneyInOutType");
            return (Criteria) this;
        }

        public Criteria andMoneyInOutTypeLessThan(Integer value) {
            addCriterion("money_in_out_type <", value, "moneyInOutType");
            return (Criteria) this;
        }

        public Criteria andMoneyInOutTypeLessThanOrEqualTo(Integer value) {
            addCriterion("money_in_out_type <=", value, "moneyInOutType");
            return (Criteria) this;
        }

        public Criteria andMoneyInOutTypeIn(List<Integer> values) {
            addCriterion("money_in_out_type in", values, "moneyInOutType");
            return (Criteria) this;
        }

        public Criteria andMoneyInOutTypeNotIn(List<Integer> values) {
            addCriterion("money_in_out_type not in", values, "moneyInOutType");
            return (Criteria) this;
        }

        public Criteria andMoneyInOutTypeBetween(Integer value1, Integer value2) {
            addCriterion("money_in_out_type between", value1, value2, "moneyInOutType");
            return (Criteria) this;
        }

        public Criteria andMoneyInOutTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("money_in_out_type not between", value1, value2, "moneyInOutType");
            return (Criteria) this;
        }

        public Criteria andUserTransRecordIdIsNull() {
            addCriterion("user_trans_record_id is null");
            return (Criteria) this;
        }

        public Criteria andUserTransRecordIdIsNotNull() {
            addCriterion("user_trans_record_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserTransRecordIdEqualTo(Integer value) {
            addCriterion("user_trans_record_id =", value, "userTransRecordId");
            return (Criteria) this;
        }

        public Criteria andUserTransRecordIdNotEqualTo(Integer value) {
            addCriterion("user_trans_record_id <>", value, "userTransRecordId");
            return (Criteria) this;
        }

        public Criteria andUserTransRecordIdGreaterThan(Integer value) {
            addCriterion("user_trans_record_id >", value, "userTransRecordId");
            return (Criteria) this;
        }

        public Criteria andUserTransRecordIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_trans_record_id >=", value, "userTransRecordId");
            return (Criteria) this;
        }

        public Criteria andUserTransRecordIdLessThan(Integer value) {
            addCriterion("user_trans_record_id <", value, "userTransRecordId");
            return (Criteria) this;
        }

        public Criteria andUserTransRecordIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_trans_record_id <=", value, "userTransRecordId");
            return (Criteria) this;
        }

        public Criteria andUserTransRecordIdIn(List<Integer> values) {
            addCriterion("user_trans_record_id in", values, "userTransRecordId");
            return (Criteria) this;
        }

        public Criteria andUserTransRecordIdNotIn(List<Integer> values) {
            addCriterion("user_trans_record_id not in", values, "userTransRecordId");
            return (Criteria) this;
        }

        public Criteria andUserTransRecordIdBetween(Integer value1, Integer value2) {
            addCriterion("user_trans_record_id between", value1, value2, "userTransRecordId");
            return (Criteria) this;
        }

        public Criteria andUserTransRecordIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_trans_record_id not between", value1, value2, "userTransRecordId");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}