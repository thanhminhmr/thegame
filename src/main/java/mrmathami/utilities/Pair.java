package mrmathami.utilities;

import java.io.Serializable;
import java.util.Objects;

public interface Pair<A, B> {
	static <A, B> Pair<A, B> mutableOf(A a, B b) {
		return new MutableUnorderedPair<>(a, b);
	}

	static <A, B> Pair<A, B> immutableOf(A a, B b) {
		return new ImmutableUnorderedPair<>(a, b);
	}

	A getA();

	A setA(A a) throws UnsupportedOperationException;

	B getB();

	B setB(B b) throws UnsupportedOperationException;
}

final class MutablePair<A, B> implements Pair<A, B>, Serializable {
	private static final long serialVersionUID = 2832741990779104860L;
	private A a;
	private B b;

	MutablePair(A a, B b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public final A getA() {
		return a;
	}

	@Override
	public final A setA(A a) throws UnsupportedOperationException {
		final A oldA = this.a;
		this.a = a;
		return oldA;
	}

	@Override
	public final B getB() {
		return b;
	}

	@Override
	public final B setB(B b) throws UnsupportedOperationException {
		final B oldB = this.b;
		this.b = b;
		return oldB;
	}

	@Override
	public final boolean equals(Object object) {
		if (this == object) return true;
		if (!(object instanceof Pair)) return false;
		final Pair<?, ?> pair = (Pair<?, ?>) object;
		return Objects.equals(a, pair.getA()) && Objects.equals(b, pair.getB());
	}

	@Override
	public final int hashCode() {
		return Objects.hash(a, b);
	}

	@Override
	public final String toString() {
		return String.format("{\n\t%s,\n\t%s\n}", a, b);
	}
}

final class ImmutablePair<A, B> implements Pair<A, B>, Serializable {
	private static final long serialVersionUID = 5947056036718994601L;
	private final A a;
	private final B b;

	ImmutablePair(A a, B b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public final A getA() {
		return a;
	}

	@Override
	public final A setA(A a) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Immutable pair can't be modified.");
	}

	@Override
	public final B getB() {
		return b;
	}

	@Override
	public final B setB(B b) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Immutable pair can't be modified.");
	}

	@Override
	public final boolean equals(Object object) {
		if (this == object) return true;
		if (!(object instanceof Pair)) return false;
		final Pair<?, ?> pair = (Pair<?, ?>) object;
		return Objects.equals(a, pair.getA()) && Objects.equals(b, pair.getB());
	}

	@Override
	public final int hashCode() {
		return Objects.hash(a, b);
	}

	@Override
	public final String toString() {
		return String.format("{\n\t%s,\n\t%s\n}", a, b);
	}
}
