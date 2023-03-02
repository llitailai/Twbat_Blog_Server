package com.twbat.blog.common.util.util.exception;

import com.twbat.blog.common.util.util.exception.base.BaseException;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/10 - 21:08
 * @desciption
 */
public class FileNameLengthLimitExceededException extends BaseException {

    private static final long serialVersionUID = 1L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength) {
        super("upload.filename.exceed.length", new Object[]{defaultFileNameLength});
    }
}
