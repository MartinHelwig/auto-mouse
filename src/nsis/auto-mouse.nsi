#SPDX-FileCopyrightText: 2023 Martin Helwig
#
#SPDX-License-Identifier: MIT

Name "auto-mouse"

XPStyle on

BrandingText "Martin Helwig"

Function .onInit
	InitPluginsDir
	File /oname=$PLUGINSDIR\splash.bmp "splash.bmp"

	splash::show 3000 $PLUGINSDIR\splash

	Pop $0
FunctionEnd

Section
SectionEnd