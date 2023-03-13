#SPDX-FileCopyrightText: 2023 Martin Helwig
#
#SPDX-License-Identifier: MIT

###suseprime-appindicator

Summary:    Auto Mouse Utility
Name:       auto-mouse
Version:    $VERSION$
Release:    $RELEASE$
License:    MIT
URL:        https://github.com/MartinHelwig/auto-mouse
BUGURL:     https://github.com/MartinHelwig/auto-mouse/issues
Packager:   Martin Helwig
BuildArch:  noarch

%description
Auto Mouse Utility

%prep
echo "BUILDROOT = $RPM_BUILD_ROOT"
mkdir -p $RPM_BUILD_ROOT/opt/martinhelwig

cd $RPM_BUILD_ROOT/opt/martinhelwig
tar xvfz $RPM_BUILD_ROOT/../../../../dist/auto-mouse.tar.gz
cd auto-mouse
exit

%files
/opt/martinhelwig/auto-mouse/**

%clean
cp $RPM_BUILD_ROOT/../../RPMS/noarch/*.rpm $RPM_BUILD_ROOT/../../../../dist

%pre
if [ "$1" == "1" ]; then
   echo "This is the initial installation of auto-mouse - pre"
fi
if [ "$1" == "2" ]; then
   echo "This is an upgrade installation of auto-mouse - pre"
fi

%post
if [ "$1" == "1" ]; then
   echo "This is the initial installation of auto-mouse - post"
   date > /opt/martinhelwig/auto-mouse/.installed
fi
if [ "$1" == "2" ]; then
   echo "This is an upgrade installation of auto-mouse - post"
fi

%postun
if [ "$1" == "0" ]; then
   echo "This is an uninstallation of auto-mouse - postun"
   rm -rf /opt/martinhelwig/auto-mouse
   rmdir /opt/martinhelwig
fi
if [ "$1" == "1" ]; then
   echo "This is an upgrade installation of auto-mouse - postun"
fi
