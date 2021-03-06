package jadx.tests.integration.names;

import org.junit.Test;

import jadx.core.dex.nodes.ClassNode;
import jadx.tests.api.SmaliTest;

import static jadx.tests.api.utils.JadxMatchers.containsOne;
import static org.junit.Assert.assertThat;

public class TestDuplicatedNames extends SmaliTest {
/*
	public static class TestCls {

		public Object fieldName;
		public String fieldName;

		public Object run() {
			return this.fieldName;
		}

		public String run() {
			return this.fieldName;
		}
	}
*/
	@Test
	public void test() {
		commonChecks();
	}

	@Test
	public void testWithDeobf() {
		enableDeobfuscation();
		commonChecks();
	}

	private void commonChecks() {
		ClassNode cls = getClassNodeFromSmaliWithPath("names", "TestDuplicatedNames");
		String code = cls.getCode().toString();

		assertThat(code, containsOne("Object fieldName;"));
		assertThat(code, containsOne("String f0fieldName"));

		assertThat(code, containsOne("this.fieldName"));
		assertThat(code, containsOne("this.f0fieldName"));

		assertThat(code, containsOne("public Object run() {"));
		assertThat(code, containsOne("public String m1run() {"));
	}
}
