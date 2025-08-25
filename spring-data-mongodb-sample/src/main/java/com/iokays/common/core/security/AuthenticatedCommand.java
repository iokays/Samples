package com.iokays.common.core.security;

import com.iokays.common.core.command.Command;

/**
 * 已授权的命令接口
 */
public interface AuthenticatedCommand extends Command {

    AuthenticatedUser authenticatedUser();

}
