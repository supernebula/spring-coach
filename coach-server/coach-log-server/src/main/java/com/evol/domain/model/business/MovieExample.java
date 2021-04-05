package com.evol.domain.model.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MovieExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andForeignNameIsNull() {
            addCriterion("foreign_name is null");
            return (Criteria) this;
        }

        public Criteria andForeignNameIsNotNull() {
            addCriterion("foreign_name is not null");
            return (Criteria) this;
        }

        public Criteria andForeignNameEqualTo(String value) {
            addCriterion("foreign_name =", value, "foreignName");
            return (Criteria) this;
        }

        public Criteria andForeignNameNotEqualTo(String value) {
            addCriterion("foreign_name <>", value, "foreignName");
            return (Criteria) this;
        }

        public Criteria andForeignNameGreaterThan(String value) {
            addCriterion("foreign_name >", value, "foreignName");
            return (Criteria) this;
        }

        public Criteria andForeignNameGreaterThanOrEqualTo(String value) {
            addCriterion("foreign_name >=", value, "foreignName");
            return (Criteria) this;
        }

        public Criteria andForeignNameLessThan(String value) {
            addCriterion("foreign_name <", value, "foreignName");
            return (Criteria) this;
        }

        public Criteria andForeignNameLessThanOrEqualTo(String value) {
            addCriterion("foreign_name <=", value, "foreignName");
            return (Criteria) this;
        }

        public Criteria andForeignNameLike(String value) {
            addCriterion("foreign_name like", value, "foreignName");
            return (Criteria) this;
        }

        public Criteria andForeignNameNotLike(String value) {
            addCriterion("foreign_name not like", value, "foreignName");
            return (Criteria) this;
        }

        public Criteria andForeignNameIn(List<String> values) {
            addCriterion("foreign_name in", values, "foreignName");
            return (Criteria) this;
        }

        public Criteria andForeignNameNotIn(List<String> values) {
            addCriterion("foreign_name not in", values, "foreignName");
            return (Criteria) this;
        }

        public Criteria andForeignNameBetween(String value1, String value2) {
            addCriterion("foreign_name between", value1, value2, "foreignName");
            return (Criteria) this;
        }

        public Criteria andForeignNameNotBetween(String value1, String value2) {
            addCriterion("foreign_name not between", value1, value2, "foreignName");
            return (Criteria) this;
        }

        public Criteria andReleaseDateIsNull() {
            addCriterion("release_date is null");
            return (Criteria) this;
        }

        public Criteria andReleaseDateIsNotNull() {
            addCriterion("release_date is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseDateEqualTo(Date value) {
            addCriterion("release_date =", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateNotEqualTo(Date value) {
            addCriterion("release_date <>", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateGreaterThan(Date value) {
            addCriterion("release_date >", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateGreaterThanOrEqualTo(Date value) {
            addCriterion("release_date >=", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateLessThan(Date value) {
            addCriterion("release_date <", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateLessThanOrEqualTo(Date value) {
            addCriterion("release_date <=", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateIn(List<Date> values) {
            addCriterion("release_date in", values, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateNotIn(List<Date> values) {
            addCriterion("release_date not in", values, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateBetween(Date value1, Date value2) {
            addCriterion("release_date between", value1, value2, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateNotBetween(Date value1, Date value2) {
            addCriterion("release_date not between", value1, value2, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andMinutesIsNull() {
            addCriterion("minutes is null");
            return (Criteria) this;
        }

        public Criteria andMinutesIsNotNull() {
            addCriterion("minutes is not null");
            return (Criteria) this;
        }

        public Criteria andMinutesEqualTo(Integer value) {
            addCriterion("minutes =", value, "minutes");
            return (Criteria) this;
        }

        public Criteria andMinutesNotEqualTo(Integer value) {
            addCriterion("minutes <>", value, "minutes");
            return (Criteria) this;
        }

        public Criteria andMinutesGreaterThan(Integer value) {
            addCriterion("minutes >", value, "minutes");
            return (Criteria) this;
        }

        public Criteria andMinutesGreaterThanOrEqualTo(Integer value) {
            addCriterion("minutes >=", value, "minutes");
            return (Criteria) this;
        }

        public Criteria andMinutesLessThan(Integer value) {
            addCriterion("minutes <", value, "minutes");
            return (Criteria) this;
        }

        public Criteria andMinutesLessThanOrEqualTo(Integer value) {
            addCriterion("minutes <=", value, "minutes");
            return (Criteria) this;
        }

        public Criteria andMinutesIn(List<Integer> values) {
            addCriterion("minutes in", values, "minutes");
            return (Criteria) this;
        }

        public Criteria andMinutesNotIn(List<Integer> values) {
            addCriterion("minutes not in", values, "minutes");
            return (Criteria) this;
        }

        public Criteria andMinutesBetween(Integer value1, Integer value2) {
            addCriterion("minutes between", value1, value2, "minutes");
            return (Criteria) this;
        }

        public Criteria andMinutesNotBetween(Integer value1, Integer value2) {
            addCriterion("minutes not between", value1, value2, "minutes");
            return (Criteria) this;
        }

        public Criteria andDiscountCountIsNull() {
            addCriterion("discount_count is null");
            return (Criteria) this;
        }

        public Criteria andDiscountCountIsNotNull() {
            addCriterion("discount_count is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountCountEqualTo(Integer value) {
            addCriterion("discount_count =", value, "discountCount");
            return (Criteria) this;
        }

        public Criteria andDiscountCountNotEqualTo(Integer value) {
            addCriterion("discount_count <>", value, "discountCount");
            return (Criteria) this;
        }

        public Criteria andDiscountCountGreaterThan(Integer value) {
            addCriterion("discount_count >", value, "discountCount");
            return (Criteria) this;
        }

        public Criteria andDiscountCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("discount_count >=", value, "discountCount");
            return (Criteria) this;
        }

        public Criteria andDiscountCountLessThan(Integer value) {
            addCriterion("discount_count <", value, "discountCount");
            return (Criteria) this;
        }

        public Criteria andDiscountCountLessThanOrEqualTo(Integer value) {
            addCriterion("discount_count <=", value, "discountCount");
            return (Criteria) this;
        }

        public Criteria andDiscountCountIn(List<Integer> values) {
            addCriterion("discount_count in", values, "discountCount");
            return (Criteria) this;
        }

        public Criteria andDiscountCountNotIn(List<Integer> values) {
            addCriterion("discount_count not in", values, "discountCount");
            return (Criteria) this;
        }

        public Criteria andDiscountCountBetween(Integer value1, Integer value2) {
            addCriterion("discount_count between", value1, value2, "discountCount");
            return (Criteria) this;
        }

        public Criteria andDiscountCountNotBetween(Integer value1, Integer value2) {
            addCriterion("discount_count not between", value1, value2, "discountCount");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceIsNull() {
            addCriterion("discount_price is null");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceIsNotNull() {
            addCriterion("discount_price is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceEqualTo(Integer value) {
            addCriterion("discount_price =", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceNotEqualTo(Integer value) {
            addCriterion("discount_price <>", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceGreaterThan(Integer value) {
            addCriterion("discount_price >", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("discount_price >=", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceLessThan(Integer value) {
            addCriterion("discount_price <", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceLessThanOrEqualTo(Integer value) {
            addCriterion("discount_price <=", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceIn(List<Integer> values) {
            addCriterion("discount_price in", values, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceNotIn(List<Integer> values) {
            addCriterion("discount_price not in", values, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceBetween(Integer value1, Integer value2) {
            addCriterion("discount_price between", value1, value2, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("discount_price not between", value1, value2, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andReleaseRegionIsNull() {
            addCriterion("release_region is null");
            return (Criteria) this;
        }

        public Criteria andReleaseRegionIsNotNull() {
            addCriterion("release_region is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseRegionEqualTo(String value) {
            addCriterion("release_region =", value, "releaseRegion");
            return (Criteria) this;
        }

        public Criteria andReleaseRegionNotEqualTo(String value) {
            addCriterion("release_region <>", value, "releaseRegion");
            return (Criteria) this;
        }

        public Criteria andReleaseRegionGreaterThan(String value) {
            addCriterion("release_region >", value, "releaseRegion");
            return (Criteria) this;
        }

        public Criteria andReleaseRegionGreaterThanOrEqualTo(String value) {
            addCriterion("release_region >=", value, "releaseRegion");
            return (Criteria) this;
        }

        public Criteria andReleaseRegionLessThan(String value) {
            addCriterion("release_region <", value, "releaseRegion");
            return (Criteria) this;
        }

        public Criteria andReleaseRegionLessThanOrEqualTo(String value) {
            addCriterion("release_region <=", value, "releaseRegion");
            return (Criteria) this;
        }

        public Criteria andReleaseRegionLike(String value) {
            addCriterion("release_region like", value, "releaseRegion");
            return (Criteria) this;
        }

        public Criteria andReleaseRegionNotLike(String value) {
            addCriterion("release_region not like", value, "releaseRegion");
            return (Criteria) this;
        }

        public Criteria andReleaseRegionIn(List<String> values) {
            addCriterion("release_region in", values, "releaseRegion");
            return (Criteria) this;
        }

        public Criteria andReleaseRegionNotIn(List<String> values) {
            addCriterion("release_region not in", values, "releaseRegion");
            return (Criteria) this;
        }

        public Criteria andReleaseRegionBetween(String value1, String value2) {
            addCriterion("release_region between", value1, value2, "releaseRegion");
            return (Criteria) this;
        }

        public Criteria andReleaseRegionNotBetween(String value1, String value2) {
            addCriterion("release_region not between", value1, value2, "releaseRegion");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeIsNull() {
            addCriterion("space_type is null");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeIsNotNull() {
            addCriterion("space_type is not null");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeEqualTo(Integer value) {
            addCriterion("space_type =", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeNotEqualTo(Integer value) {
            addCriterion("space_type <>", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeGreaterThan(Integer value) {
            addCriterion("space_type >", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("space_type >=", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeLessThan(Integer value) {
            addCriterion("space_type <", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeLessThanOrEqualTo(Integer value) {
            addCriterion("space_type <=", value, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeIn(List<Integer> values) {
            addCriterion("space_type in", values, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeNotIn(List<Integer> values) {
            addCriterion("space_type not in", values, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeBetween(Integer value1, Integer value2) {
            addCriterion("space_type between", value1, value2, "spaceType");
            return (Criteria) this;
        }

        public Criteria andSpaceTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("space_type not between", value1, value2, "spaceType");
            return (Criteria) this;
        }

        public Criteria andCoverUriIsNull() {
            addCriterion("cover_uri is null");
            return (Criteria) this;
        }

        public Criteria andCoverUriIsNotNull() {
            addCriterion("cover_uri is not null");
            return (Criteria) this;
        }

        public Criteria andCoverUriEqualTo(String value) {
            addCriterion("cover_uri =", value, "coverUri");
            return (Criteria) this;
        }

        public Criteria andCoverUriNotEqualTo(String value) {
            addCriterion("cover_uri <>", value, "coverUri");
            return (Criteria) this;
        }

        public Criteria andCoverUriGreaterThan(String value) {
            addCriterion("cover_uri >", value, "coverUri");
            return (Criteria) this;
        }

        public Criteria andCoverUriGreaterThanOrEqualTo(String value) {
            addCriterion("cover_uri >=", value, "coverUri");
            return (Criteria) this;
        }

        public Criteria andCoverUriLessThan(String value) {
            addCriterion("cover_uri <", value, "coverUri");
            return (Criteria) this;
        }

        public Criteria andCoverUriLessThanOrEqualTo(String value) {
            addCriterion("cover_uri <=", value, "coverUri");
            return (Criteria) this;
        }

        public Criteria andCoverUriLike(String value) {
            addCriterion("cover_uri like", value, "coverUri");
            return (Criteria) this;
        }

        public Criteria andCoverUriNotLike(String value) {
            addCriterion("cover_uri not like", value, "coverUri");
            return (Criteria) this;
        }

        public Criteria andCoverUriIn(List<String> values) {
            addCriterion("cover_uri in", values, "coverUri");
            return (Criteria) this;
        }

        public Criteria andCoverUriNotIn(List<String> values) {
            addCriterion("cover_uri not in", values, "coverUri");
            return (Criteria) this;
        }

        public Criteria andCoverUriBetween(String value1, String value2) {
            addCriterion("cover_uri between", value1, value2, "coverUri");
            return (Criteria) this;
        }

        public Criteria andCoverUriNotBetween(String value1, String value2) {
            addCriterion("cover_uri not between", value1, value2, "coverUri");
            return (Criteria) this;
        }

        public Criteria andImagesIsNull() {
            addCriterion("images is null");
            return (Criteria) this;
        }

        public Criteria andImagesIsNotNull() {
            addCriterion("images is not null");
            return (Criteria) this;
        }

        public Criteria andImagesEqualTo(String value) {
            addCriterion("images =", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesNotEqualTo(String value) {
            addCriterion("images <>", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesGreaterThan(String value) {
            addCriterion("images >", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesGreaterThanOrEqualTo(String value) {
            addCriterion("images >=", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesLessThan(String value) {
            addCriterion("images <", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesLessThanOrEqualTo(String value) {
            addCriterion("images <=", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesLike(String value) {
            addCriterion("images like", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesNotLike(String value) {
            addCriterion("images not like", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesIn(List<String> values) {
            addCriterion("images in", values, "images");
            return (Criteria) this;
        }

        public Criteria andImagesNotIn(List<String> values) {
            addCriterion("images not in", values, "images");
            return (Criteria) this;
        }

        public Criteria andImagesBetween(String value1, String value2) {
            addCriterion("images between", value1, value2, "images");
            return (Criteria) this;
        }

        public Criteria andImagesNotBetween(String value1, String value2) {
            addCriterion("images not between", value1, value2, "images");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andRatingsIsNull() {
            addCriterion("ratings is null");
            return (Criteria) this;
        }

        public Criteria andRatingsIsNotNull() {
            addCriterion("ratings is not null");
            return (Criteria) this;
        }

        public Criteria andRatingsEqualTo(Integer value) {
            addCriterion("ratings =", value, "ratings");
            return (Criteria) this;
        }

        public Criteria andRatingsNotEqualTo(Integer value) {
            addCriterion("ratings <>", value, "ratings");
            return (Criteria) this;
        }

        public Criteria andRatingsGreaterThan(Integer value) {
            addCriterion("ratings >", value, "ratings");
            return (Criteria) this;
        }

        public Criteria andRatingsGreaterThanOrEqualTo(Integer value) {
            addCriterion("ratings >=", value, "ratings");
            return (Criteria) this;
        }

        public Criteria andRatingsLessThan(Integer value) {
            addCriterion("ratings <", value, "ratings");
            return (Criteria) this;
        }

        public Criteria andRatingsLessThanOrEqualTo(Integer value) {
            addCriterion("ratings <=", value, "ratings");
            return (Criteria) this;
        }

        public Criteria andRatingsIn(List<Integer> values) {
            addCriterion("ratings in", values, "ratings");
            return (Criteria) this;
        }

        public Criteria andRatingsNotIn(List<Integer> values) {
            addCriterion("ratings not in", values, "ratings");
            return (Criteria) this;
        }

        public Criteria andRatingsBetween(Integer value1, Integer value2) {
            addCriterion("ratings between", value1, value2, "ratings");
            return (Criteria) this;
        }

        public Criteria andRatingsNotBetween(Integer value1, Integer value2) {
            addCriterion("ratings not between", value1, value2, "ratings");
            return (Criteria) this;
        }

        public Criteria andLanguageIsNull() {
            addCriterion("language is null");
            return (Criteria) this;
        }

        public Criteria andLanguageIsNotNull() {
            addCriterion("language is not null");
            return (Criteria) this;
        }

        public Criteria andLanguageEqualTo(String value) {
            addCriterion("language =", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotEqualTo(String value) {
            addCriterion("language <>", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageGreaterThan(String value) {
            addCriterion("language >", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageGreaterThanOrEqualTo(String value) {
            addCriterion("language >=", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageLessThan(String value) {
            addCriterion("language <", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageLessThanOrEqualTo(String value) {
            addCriterion("language <=", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageLike(String value) {
            addCriterion("language like", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotLike(String value) {
            addCriterion("language not like", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageIn(List<String> values) {
            addCriterion("language in", values, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotIn(List<String> values) {
            addCriterion("language not in", values, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageBetween(String value1, String value2) {
            addCriterion("language between", value1, value2, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotBetween(String value1, String value2) {
            addCriterion("language not between", value1, value2, "language");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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