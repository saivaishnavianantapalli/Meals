package com.example.vaishu.mealscapstone;

/**
 * Created by Vaishu on 12-01-2019.
 */

public class Pojo {
    String area;
    String name,image;
    String mealname,mealimage,category,ingredient1,ingredient2,ingredient3,ingredient4,ingredient5,ingredient6,measure1,measure2,measure3,measure4,measure5,measure6,instructions,source,link;
    public Pojo(String area) {
        this.area=area;
    }

    public Pojo(String name, String image) {
        this.name=name;
        this.image=image;
    }

    public Pojo(String mealname, String mealimage, String category, String ingredient1, String ingredient2, String ingredient3, String ingredient4, String ingredient5, String ingredient6, String measure1, String measure2, String measure3, String measure4, String measure5, String measure6, String instructions, String source, String link) {
        this.mealname=mealname;
        this.mealimage=mealimage;
        this.category=category;
        this.ingredient1=ingredient1;
        this.ingredient2=ingredient2;
        this.ingredient3=ingredient3;
        this.ingredient4=ingredient4;
        this.ingredient5=ingredient5;
        this.ingredient6=ingredient6;
        this.measure1=measure1;
        this.measure2=measure2;
        this.measure3=measure3;
        this.measure4=measure4;
        this.measure5=measure5;
        this.measure6=measure6;
        this.instructions=instructions;
        this.source=source;
        this.link=link;
    }

    public String getMealname() {
        return mealname;
    }

    public void setMealname(String mealname) {
        this.mealname = mealname;
    }

    public String getMealimage() {
        return mealimage;
    }

    public void setMealimage(String mealimage) {
        this.mealimage = mealimage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIngredient1() {
        return ingredient1;
    }

    public void setIngredient1(String ingredient1) {
        this.ingredient1 = ingredient1;
    }

    public String getIngredient2() {
        return ingredient2;
    }

    public void setIngredient2(String ingredient2) {
        this.ingredient2 = ingredient2;
    }

    public String getIngredient3() {
        return ingredient3;
    }

    public void setIngredient3(String ingredient3) {
        this.ingredient3 = ingredient3;
    }

    public String getIngredient4() {
        return ingredient4;
    }

    public void setIngredient4(String ingredient4) {
        this.ingredient4 = ingredient4;
    }

    public String getIngredient5() {
        return ingredient5;
    }

    public void setIngredient5(String ingredient5) {
        this.ingredient5 = ingredient5;
    }

    public String getIngredient6() {
        return ingredient6;
    }

    public void setIngredient6(String ingredient6) {
        this.ingredient6 = ingredient6;
    }

    public String getMeasure1() {
        return measure1;
    }

    public void setMeasure1(String measure1) {
        this.measure1 = measure1;
    }

    public String getMeasure2() {
        return measure2;
    }

    public void setMeasure2(String measure2) {
        this.measure2 = measure2;
    }

    public String getMeasure3() {
        return measure3;
    }

    public void setMeasure3(String measure3) {
        this.measure3 = measure3;
    }

    public String getMeasure4() {
        return measure4;
    }

    public void setMeasure4(String measure4) {
        this.measure4 = measure4;
    }

    public String getMeasure5() {
        return measure5;
    }

    public void setMeasure5(String measure5) {
        this.measure5 = measure5;
    }

    public String getMeasure6() {
        return measure6;
    }

    public void setMeasure6(String measure6) {
        this.measure6 = measure6;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
