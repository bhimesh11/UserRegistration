package com.user.registration.Service;

import java.util.function.Predicate;

public class EmailValidator implements Predicate<String>
{
    @Override
public boolean test(String s)
{
    return true;
}
}
