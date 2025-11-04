interface x1
{
    void m1();
    void m2();
}

interface x2 extends x1
{
    void m3();
    void m4();
}

abstract class A implements x2
{
    abstract public void m5();
}

class MyClass extends A
{
    @Override
    public void m1(){}
    
    @Override
    public void m2(){}
    
    @Override
    public void m3(){}
    
    @Override
    public void m4(){}
    
    @Override
    public void m5(){}
}
