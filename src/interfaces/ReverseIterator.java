package interfaces;

public interface ReverseIterator<E> {

	public boolean hasPrevious();
	public E previous();
	public void remove();

}
