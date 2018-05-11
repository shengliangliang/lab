package com.king.generic.test001;

public class Generic<T> {

    private T key;

    public Generic(T key){
        this.key = key;
    }


    //我想说的其实是这个，虽然在方法中使用了泛型，但是这并不是一个泛型方法。
    //这只是类中一个普通的成员方法，只不过他的返回值是在声明泛型类已经声明过的泛型。
    //所以在这个方法中才可以继续使用 T 这个泛型。
    public T getKey(){
        return key;
    }

    //这也不是一个泛型方法，这就是一个普通的方法，只是使用了Generic<Number>这个泛型类做形参而已。
    public Number showKeyValue1(Generic<Number> obj){
        System.out.println(obj.getKey());
        return obj.getKey();
    }

    /*
    *
    * 类型通配符一般是使用？代替具体的类型实参，注意了，此处’？’是类型实参，而不是类型形参 。重要说三遍！
    * 此处’？’是类型实参，而不是类型形参 ！ 此处’？’是类型实参，而不是类型形参 ！再直白点的意思就是，此处的？和Number、String、Integer一样都是一种实际的类型，
    * 可以把？看成所有类型的父类。是一种真实的类型。
      可以解决当具体类型不确定的时候，这个通配符就是 ?  ；当操作类型时，不需要使用类型的具体功能时，只使用Object类中的功能。那么可以用 ? 通配符来表未知类型。
    * */

    //这也不是一个泛型方法，这就是一个普通的方法，只是使用了Generic<？>这个泛型类做形参而已。
    public void showKeyValue2(Generic<?> obj){
        System.out.println(obj.getKey());
        //return obj.getKey();
    }



    /**
     * 这才是一个真正的泛型方法。
     * 首先在public与返回值之间的<T>必不可少，这表明这是一个泛型方法，并且声明了一个泛型T
     * 这个T可以出现在这个泛型方法的任意位置.
     * 泛型的数量也可以为任意多个
     *    如：public <T,K> K showKeyName(Generic<T> container){
     *        ...
     *        }
     */
    public <E> E showKeyName(Generic<E> container){
        System.out.println("container key :" + container.getKey());
        //当然这个例子举的不太合适，只是为了说明泛型方法的特性。
        E test = container.getKey();
        return test;
    }
}
