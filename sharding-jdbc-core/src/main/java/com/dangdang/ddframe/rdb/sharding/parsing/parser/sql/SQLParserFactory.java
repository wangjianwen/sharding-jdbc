/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.dangdang.ddframe.rdb.sharding.parsing.parser.sql;

import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.constant.DatabaseType;
import com.dangdang.ddframe.rdb.sharding.parsing.lexer.LexerEngine;
import com.dangdang.ddframe.rdb.sharding.parsing.lexer.token.DefaultKeyword;
import com.dangdang.ddframe.rdb.sharding.parsing.lexer.token.TokenType;
import com.dangdang.ddframe.rdb.sharding.parsing.parser.exception.SQLParsingUnsupportedException;
import com.dangdang.ddframe.rdb.sharding.parsing.parser.sql.ddl.alter.AlterParserFactory;
import com.dangdang.ddframe.rdb.sharding.parsing.parser.sql.ddl.create.CreateParserFactory;
import com.dangdang.ddframe.rdb.sharding.parsing.parser.sql.ddl.drop.DropParserFactory;
import com.dangdang.ddframe.rdb.sharding.parsing.parser.sql.ddl.truncate.TruncateParserFactory;
import com.dangdang.ddframe.rdb.sharding.parsing.parser.sql.dml.delete.DeleteParserFactory;
import com.dangdang.ddframe.rdb.sharding.parsing.parser.sql.dml.insert.InsertParserFactory;
import com.dangdang.ddframe.rdb.sharding.parsing.parser.sql.dml.update.UpdateParserFactory;
import com.dangdang.ddframe.rdb.sharding.parsing.parser.sql.dql.select.SelectParserFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * SQL parser factory.
 *
 * @author zhangliang
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SQLParserFactory {
    
    /**
     * Create SQL parser.
     *
     * @param dbType database type
     * @param tokenType token type
     * @param shardingRule databases and tables sharding rule
     * @param lexerEngine lexical analysis engine
     * @return SQL parser
     */
    public static SQLParser newInstance(final DatabaseType dbType, final TokenType tokenType, final ShardingRule shardingRule, final LexerEngine lexerEngine) {
        if (!(tokenType instanceof DefaultKeyword)) {
            throw new SQLParsingUnsupportedException(tokenType);
        }
        switch ((DefaultKeyword) tokenType)  {
            case SELECT:
                return SelectParserFactory.newInstance(dbType, shardingRule, lexerEngine);
            case INSERT:
                return InsertParserFactory.newInstance(dbType, shardingRule, lexerEngine);
            case UPDATE:
                return UpdateParserFactory.newInstance(dbType, shardingRule, lexerEngine);
            case DELETE:
                return DeleteParserFactory.newInstance(dbType, shardingRule, lexerEngine);
            case CREATE:
                return CreateParserFactory.newInstance(dbType, shardingRule, lexerEngine);
            case ALTER:
                return AlterParserFactory.newInstance(dbType, shardingRule, lexerEngine);
            case DROP:
                return DropParserFactory.newInstance(dbType, shardingRule, lexerEngine);
            case TRUNCATE:
                return TruncateParserFactory.newInstance(dbType, shardingRule, lexerEngine);
            default:
                throw new SQLParsingUnsupportedException(lexerEngine.getCurrentToken().getType());
        }
    }
}
