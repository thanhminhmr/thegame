package mrmathami.utilities;

import java.io.Serializable;
import java.util.Objects;

public interface UnorderedPair<A, B> extends Pair<A, B> {
	static <A, B> UnorderedPair<A, B> mutableOf(A a, B b) {
		return new MutableUnorderedPair<>(a, b);
	}

	static <A, B> UnorderedPair<A, B> immutableOf(A a, B b) {
		return new ImmutableUnorderedPair<>(a, b);
	}
}

final class MutableUnorderedPair<A, B> implements UnorderedPair<A, B>, Serializable {
	private static final long serialVersionUID = 860936969248672597L;
	private A a;
	private B b;

	MutableUnorderedPair(A a, B b) {
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
		return Objects.equals(a, pair.getA()) && Objects.equals(b, pair.getB())
				|| object instanceof UnorderedPair && Objects.equals(a, pair.getB()) && Objects.equals(b, pair.getA());
	}

	@Override
	public final int hashCode() {
		return Objects.hashCode(a) ^ Objects.hashCode(b);
	}

	@Override
	public final String toString() {
		return String.format("{\n\t%s,\n\t%s\n}", a, b);
	}
}

final class ImmutableUnorderedPair<A, B> implements UnorderedPair<A, B>, Serializable {
	private static final long serialVersionUID = -1711976437683437744L;
	private final A a;
	private final B b;

	ImmutableUnorderedPair(A a, B b) {
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
		return Objects.equals(a, pair.getA()) && Objects.equals(b, pair.getB())
				|| object instanceof UnorderedPair && Objects.equals(a, pair.getB()) && Objects.equals(b, pair.getA());
	}

	@Override
	public final int hashCode() {
		return Objects.hashCode(a) ^ Objects.hashCode(b);
	}

	@Override
	public final String toString() {
		return String.format("{\n\t%s,\n\t%s\n}", a, b);
	}
}