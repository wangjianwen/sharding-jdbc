package com.dangdang.ddframe.rdb.sharding.parsing.parser.dialect.oracle.lexer;

import com.dangdang.ddframe.rdb.sharding.parsing.lexer.token.Assist;
import com.dangdang.ddframe.rdb.sharding.parsing.lexer.token.DefaultKeyword;
import com.dangdang.ddframe.rdb.sharding.parsing.lexer.LexerAssert;
import org.junit.Test;

public final class OracleLexerTest {
    
    @Test
    public void assertNextTokenForVariable() {
        OracleLexer lexer = new OracleLexer("SELECT @x1:=1 FROM TABLE_XXX");
        LexerAssert.assertNextToken(lexer, DefaultKeyword.SELECT, "SELECT");
        LexerAssert.assertNextToken(lexer, Assist.ERROR, "");
    }
}