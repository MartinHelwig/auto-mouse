#SPDX-FileCopyrightText: 2023 Martin Helwig
#
#SPDX-License-Identifier: MIT

#####################################################################################################

!define PRODUCT_NAME 					"${PROJECT_ARTIFACT_ID}"
!define PRODUCT_NAME_SHORT 				"${PROJECT_ARTIFACT_ID}"
!define PRODUCT_COMMENT                 "A utility to prevent screen locking."
!define PRODUCT_CONTACT                 "Martin Helwig <115872657+MartinHelwig@users.noreply.github.com>"
!define PRODUCT_EXECUTABLE				"start.cmd"
!define PRODUCT_EXECUTABLE_ICON			"icon.ico"
!define PRODUCT_PUBLISHER 				"Martin Helwig"
!define PRODUCT_WEBSITE 				"https://martinhelwig.github.io/auto-mouse"
!define PRODUCT_REGISTRY_KEY 			"Software\Microsoft\Windows\CurrentVersion\Uninstall\${PRODUCT_NAME}"
!define PRODUCT_REGISTRY_ROOT   		"HKCU"
!define PRODUCT_LICENSEFILE             "..\..\LICENSE"
!define PRODUCT_DISTRIBUTION_DIRECTORY	"..\..\target\auto-mouse"
!define PRODUCT_INSTALLDIR              "$LOCALAPPDATA\Programs\${PRODUCT_NAME_SHORT}"
!define PRODUCT_FILE_NAME               "${PROJECT_FINAL_NAME}.exe"              
#!define PRODUCT_MAJOR_VERSION			"0"
#!define PRODUCT_MINOR_VERSION			"0"
#!define PRODUCT_PATCH_VERSION			"0"
#!define PRODUCT_BUILD_VERSION			"1"
!define PRODUCT_VERSION					"${PRODUCT_MAJOR_VERSION}.${PRODUCT_MINOR_VERSION}.${PRODUCT_PATCH_VERSION}.${PRODUCT_BUILD_VERSION}"

#####################################################################################################

VIProductVersion "${PRODUCT_VERSION}"

#Descriptions for the parameter from https://learn.microsoft.com/en-us/windows/win32/menurc/stringfileinfo-block 

#Name of the product with which the file is distributed. This string is required.
VIAddVersionKey /LANG=0 "ProductName" "${PRODUCT_NAME}"

#Additional information that should be displayed for diagnostic purposes.
VIAddVersionKey /LANG=0 "Comments" "${PRODUCT_COMMENT}"

#Company that produced the file—for example, "Microsoft Corporation" or "Standard Microsystems Corporation, Inc." This string is required.
#VIAddVersionKey /LANG=0 "CompanyName" "${PRODUCT_PUBLISHER}"

#Copyright notices that apply to the file. This should include the full text of all notices, legal symbols, copyright dates, and so on. This string is optional.
VIAddVersionKey /LANG=0 "LegalCopyright" "Copyright © 2023 ${PRODUCT_PUBLISHER}"

#File description to be presented to users. This string may be displayed in a list box when the user is choosing files to install—for example, "Keyboard Driver for AT-Style Keyboards". This string is required.
VIAddVersionKey /LANG=0 "FileDescription" "${PRODUCT_COMMENT}"

#Version number of the file—for example, "3.10" or "5.00.RC2". This string is required.
VIAddVersionKey /LANG=0 "FileVersion" "${PRODUCT_VERSION}"

#Version of the product with which the file is distributed—for example, "3.10" or "5.00.RC2". This string is required.
VIAddVersionKey /LANG=0 "ProductVersion" "${PRODUCT_VERSION}"

#Internal name of the file, if one exists—for example, a module name if the file is a dynamic-link library. If the file has no internal name, this string should be the original filename, without extension. This string is required.
VIAddVersionKey /LANG=0 "InternalName" "${PRODUCT_NAME_SHORT}"

#Original name of the file, not including a path. This information enables an application to determine whether a file has been renamed by a user. The format of the name depends on the file system for which the file was created. This string is required.
VIAddVersionKey /LANG=0 "OriginalFilename" "${PRODUCT_FILE_NAME}"

#Information about a private version of the file—for example, "Built by TESTER1 on \TESTBED". This string should be present only if VS_FF_PRIVATEBUILD is specified in the fileflags parameter of the root block.
#VIAddVersionKey /LANG=0 "PrivateBuild" ""

#Text that specifies how this version of the file differs from the standard version—for example, "Private build for TESTER1 solving mouse problems on M250 and M250E computers". This string should be present only if VS_FF_SPECIALBUILD is specified in the fileflags parameter of the root block.
#VIAddVersionKey /LANG=0 "SpecialBuild" ""

#Trademarks and registered trademarks that apply to the file. This should include the full text of all notices, legal symbols, trademark numbers, and so on. This string is optional.
#VIAddVersionKey /LANG=0 "LegalTrademarks" ""

#####################################################################################################

!include "MUI2.nsh"
!include "FileFunc.nsh"
!include "WordFunc.nsh"

### General Settings
Name "${PRODUCT_NAME}"
BrandingText "${PRODUCT_PUBLISHER}"
RequestExecutionLevel User
Unicode true
ShowInstDetails hide
ShowUninstDetails hide
InstallDirRegKey "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "InstallLocation"

### Interface Settings
!define MUI_ABORTWARNING
!define MUI_ICON "resources\icon.ico"
!define MUI_UNICON "resources\icon.ico"
!define MUI_WELCOMEPAGE_TITLE_3LINES
!define MUI_HEADERIMAGE
!define MUI_HEADERIMAGE_RIGHT
!define MUI_HEADERIMAGE_BITMAP "resources\branding.bmp"
!define MUI_WELCOMEFINISHPAGE_BITMAP "resources\welcome.bmp"
!define MUI_FINISHPAGE_TITLE_3LINES
!define MUI_FINISHPAGE_RUN "$INSTDIR\${PRODUCT_EXECUTABLE}"
!define MUI_FINISHPAGE_LINK "${PRODUCT_PUBLISHER}"
!define MUI_FINISHPAGE_LINK_LOCATION "${PRODUCT_WEBSITE}"
!define MUI_FINISHPAGE_NOAUTOCLOSE
!define MUI_STARTMENUPAGE_NODISABLE
!define MUI_LICENSEPAGE_CHECKBOX
!define MUI_LANGDLL_ALLLANGUAGES
!define MUI_LANGDLL_REGISTRY_ROOT "${PRODUCT_REGISTRY_ROOT}"
!define MUI_LANGDLL_REGISTRY_KEY "${PRODUCT_REGISTRY_KEY}"
!define MUI_LANGDLL_REGISTRY_VALUENAME "Installer Language"

### Pages
!define MUI_PAGE_CUSTOMFUNCTION_PRE "maybeAbort"
!insertmacro MUI_PAGE_WELCOME
!define MUI_PAGE_CUSTOMFUNCTION_PRE "maybeAbort"
!insertmacro MUI_PAGE_LICENSE "${PRODUCT_LICENSEFILE}"
!define MUI_PAGE_CUSTOMFUNCTION_PRE "maybeAbort"
!insertmacro MUI_PAGE_DIRECTORY
!insertmacro MUI_PAGE_INSTFILES
!insertmacro MUI_PAGE_FINISH

!insertmacro MUI_UNPAGE_CONFIRM
!insertmacro MUI_UNPAGE_INSTFILES

### Languages
!insertmacro MUI_LANGUAGE "English" ;first language is the default language
!insertmacro MUI_LANGUAGE "German"
!insertmacro MUI_RESERVEFILE_LANGDLL

LangString AlreadyInstalledMessage ${LANG_ENGLISH} "${PRODUCT_NAME} ${PRODUCT_VERSION} is already installed. Do you want to reinstall?"
LangString AlreadyInstalledMessage ${LANG_GERMAN} "${PRODUCT_NAME} ${PRODUCT_VERSION} ist bereits installiert. Wollen Sie eine Reparaturinstallation durchführen?"

LangString UpdateMessage ${LANG_ENGLISH} "${PRODUCT_NAME} will be updated."
LangString UpdateMessage ${LANG_GERMAN} "${PRODUCT_NAME} wird jetzt aktualisiert."

LangString DowngradeMessage ${LANG_ENGLISH} "${PRODUCT_NAME} will be downgraded."
LangString DowngradeMessage ${LANG_GERMAN} "${PRODUCT_NAME} wird jetzt auf eine ältere Version zurückgesetzt."

### Functions
Function .onInit
    Var /GLOBAL InstallMode
    StrCpy $InstallMode "3"
    
    ${If} "$INSTDIR" == ""
        StrCpy $INSTDIR "${PRODUCT_INSTALLDIR}"
    ${EndIf}
    
    InitPluginsDir
	File /oname=$PLUGINSDIR\splash.bmp "resources\splash.bmp"
	splash::show 1000 $PLUGINSDIR\splash
    Pop $0
    
    !insertmacro MUI_LANGDLL_DISPLAY
    
    Call checkUpdate
FunctionEnd

Function maybeAbort
    ${IfNot} "$InstallMode" == "3"
        Abort
    ${EndIf}
FunctionEnd

Function checkUpdate
	ReadRegStr $R0 "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "DisplayVersion"
	${IfNot} "$R0" == ""
		${VersionCompare} "$R0" "${PRODUCT_VERSION}" $R0
		StrCpy $InstallMode "$R0"
		StrCmp "$InstallMode" "0" alreadyinstalled
		StrCmp "$InstallMode" "1" downgrade
		StrCmp "$InstallMode" "2" update
	${EndIf}
	Goto install
    
	install:
        Goto done
	quit:
		Quit
	update:
		HideWindow
		ReadRegStr $R0 "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "InstallLocation"
		${If} ${FileExists} "$R0"
			MessageBox MB_OKCANCEL|MB_ICONEXCLAMATION "$(UpdateMessage)" /SD IDOK IDCANCEL quit
		${EndIf}
		Goto done
	alreadyinstalled:
		HideWindow
		MessageBox MB_OKCANCEL|MB_ICONQUESTION "$(AlreadyInstalledMessage)" /SD IDOK IDCANCEL quit
		Goto done
	downgrade:
		HideWindow
		ReadRegStr $R0 "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "InstallLocation"
		${If} ${FileExists} "$R0"
			MessageBox MB_OKCANCEL|MB_ICONEXCLAMATION "$(DowngradeMessage)" /SD IDOK IDCANCEL quit
		${EndIf}
		Goto done
	done:
FunctionEnd

Function un.onInit
  !insertmacro MUI_UNGETLANGUAGE  
FunctionEnd

### Sections
Section -Main SEC0000
	SetOutPath "$INSTDIR"
	SetOverwrite on
	File /r "${PRODUCT_DISTRIBUTION_DIRECTORY}\*"
SectionEnd

Section -AdditionalIcons
	CreateDirectory "$SMPROGRAMS\${PRODUCT_NAME}"
	CreateShortCut "$SMPROGRAMS\${PRODUCT_NAME}\${PRODUCT_NAME}.lnk" "$INSTDIR\${PRODUCT_EXECUTABLE}" "" "$INSTDIR\uninstall.exe"
	CreateShortCut "$SMPROGRAMS\${PRODUCT_NAME}\Uninstall.lnk" "$INSTDIR\uninstall.exe"
	WriteINIStr "$SMPROGRAMS\${PRODUCT_NAME}\Information.url" "InternetShortcut" "URL" "${PRODUCT_WEBSITE}"
SectionEnd

Section -Post
	WriteUninstaller "$INSTDIR\uninstall.exe"
	
    ${GetSize} "$INSTDIR" "/S=0K" $0 $1 $2
    WriteRegDWORD "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "EstimatedSize" "$0"
    WriteRegDWORD "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "NoModify" "1"
	WriteRegDWORD "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "NoRepair" "1"
    
    WriteRegStr "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "Comments" "${PRODUCT_COMMENT}"
    WriteRegStr "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "Contact" "${PRODUCT_CONTACT}"
	WriteRegStr "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "DisplayIcon" "$INSTDIR\uninstall.exe"
    WriteRegStr "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "DisplayName" "${PRODUCT_NAME}"
    WriteRegStr "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "DisplayVersion" "${PRODUCT_VERSION}"
    WriteRegStr "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "InstallLocation" "$INSTDIR"
    WriteRegStr "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "InstallSource" "$EXEPATH"
    WriteRegStr "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "QuietUninstallString" "$INSTDIR\uninstall.exe /S"
	WriteRegStr "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "UninstallString" "$INSTDIR\uninstall.exe"
    WriteRegStr "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "Readme" "${PRODUCT_WEBSITE}"
	WriteRegStr "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "URLInfoAbout" "${PRODUCT_WEBSITE}"
    WriteRegStr "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "URLUpdateInfo" "${PRODUCT_WEBSITE}"
    WriteRegStr "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "HelpLink" "${PRODUCT_WEBSITE}"
    WriteRegStr "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "HelpTelephone" ""
	WriteRegStr "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "Publisher" "${PRODUCT_PUBLISHER}"
	WriteRegStr "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "MajorVersion" "${PRODUCT_MAJOR_VERSION}"
	WriteRegStr "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "MinorVersion" "${PRODUCT_MINOR_VERSION}"
	WriteRegStr "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "PatchVersion" "${PRODUCT_PATCH_VERSION}"
	WriteRegStr "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}" "BuildVersion" "${PRODUCT_BUILD_VERSION}"
SectionEnd

Section Uninstall
	RmDir /r /REBOOTOK "$SMPROGRAMS\${PRODUCT_NAME}"
	RmDir /r /REBOOTOK "$INSTDIR"
	DeleteRegKey "${PRODUCT_REGISTRY_ROOT}" "${PRODUCT_REGISTRY_KEY}"
	SetAutoClose false
SectionEnd