package people;

public class Pack
{
	int weight;
	String content;
	int size;
	boolean priority;
	String status;
    public Pack(int weight, String content, int size, boolean priority, String status)
    {
        this.weight = weight;
        this.content = content;
        this.size = size;
        this.priority = priority;
        this.status = status;
    }
}