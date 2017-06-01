package com.artem.currencyconverter.domain.mapper;

import java.util.List;

/**
 * Created by artemmurashov on 6/1/17.
 */

public interface Mapper<Source, Target> {
    Target map(Source source);
    List<Target> mapList(List<Source> sources);
}
