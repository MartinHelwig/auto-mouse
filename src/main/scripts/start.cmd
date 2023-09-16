REM SPDX-FileCopyrightText: 2023 Martin Helwig
REM
REM SPDX-License-Identifier: MIT

@echo off

set CURRENTDIR=%CD%
set MAINCLASS=io.github.martinhelwig.utility.automouse.AutoMouse

pushd "%~dp0"
if exist jre\bin\javaw.exe (
   jre\bin\javaw.exe -cp config;lib\* %MAINCLASS%
) else (
   start javaw.exe -cp config;lib\* %MAINCLASS%
)

pushd "%CURRENTDIR%"
