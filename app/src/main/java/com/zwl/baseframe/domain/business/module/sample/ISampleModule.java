package com.zwl.baseframe.domain.business.module.sample;


import com.zwl.baseframe.domain.business.module.impl.SampleBean;
import com.zwl.baseframe.domain.business.model.SampleModel;

import io.reactivex.Observable;
import io.reactivex.functions.Function;


/**
 * @author velen
 * @date 2017/3/23
 */
public interface ISampleModule {
    Function<SampleBean, Observable<SampleModel>> login();
}
