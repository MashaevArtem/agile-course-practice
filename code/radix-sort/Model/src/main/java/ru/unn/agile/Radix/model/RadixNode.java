package ru.unn.agile.Radix.Model;

public class RadixNode<K extends Comparable<K>, V extends Comparable<V>>
        implements Comparable<RadixNode<K, V>> 
{
    public COLOR getColor() 
    {
        return color;
    }

    public enum COLOR { GREEN, BLACK }

    @Override
    public int compareTo(final RadixNode<K, V> o) 
    {
        int cmp = this.key.compareTo(o.getKey());
        if (cmp == 0) 
        {
            return this.val.compareTo(o.getVal());
        }
        return cmp;
    }

    public RadixNode(final K key, final V value, final COLOR color) 
    {
        this.val = value;
        this.key = key;
        this.color = color;
        this.left = null;
        this.right = null;
    }

    public RadixNode(final K key, final V value) 
    {
        this(key, value, COLOR.GREEN);
    }

    public void setColor(final COLOR color) 
    {
        this.color = color;
    }

    public V getVal() 
    {
        return val;
    }

    public void setVal(final V val) 
    {
        this.val = val;
    }

    public K getKey() 
    {
        return key;
    }

    public void setKey(final K key) 
    {
        this.key = key;
    }

    public RadixNode<K, V> getLeft() 
    {
        return left;
    }

    public void setLeft(final RadixNode<K, V> left) 
    {
        this.left = left;
    }

    public RadixNode<K, V> getRight() 
    {
        return right;
    }

    public void setRight(final RadixNode<K, V> right) 
    {
        this.right = right;
    }

    public RadixNode<K, V> getParent() 
    {
        return parent;
    }

    public void setParent(final RadixNode<K, V> parent) 
    {
        this.parent = parent;
    }

    private COLOR color;
    private V val;
    private K key;
    private RadixNode<K, V> left, right, parent;
}
