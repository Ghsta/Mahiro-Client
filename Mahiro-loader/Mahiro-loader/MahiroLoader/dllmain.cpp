#define BUILD_DLL
#define _WINSOCK_DEPRECATED_NO_WARNINGS
#define NOMINMAX

#include "main.h"
#include "jni.h"
#include "jvmti.h"
#include "classes.h"
#include "Loader.h"

#include "HookLib.h"
#include "utils.h"
#include <stdio.h>
#include <string>
using namespace std;

#pragma comment(lib, "..\\libs\\Zydis.lib")
#pragma comment(lib, "..\\libs\\HookLib.lib")


char* user = NULL;
char* password = NULL;
int ct = 0;

void cheat(JNIEnv* env) {

    //VM_TIGER_BLACK_START

    //功能开始Load
    jclass classLoaderClazz = NULL;

    classLoaderClazz = env->DefineClass(NULL, NULL, (jbyte*)classLoaderClass, classLoaderClassSize);
    if (!classLoaderClazz)
    {
        jclass classMain = NULL;
        if (!classMain) {
            MessageBoxA(NULL, "不要注入两次！！！！", "错误", MB_OK | MB_ICONERROR);
            return;
        }
      
    }
  
    jobjectArray classesData = (jobjectArray)env->CallStaticObjectMethod(classLoaderClazz, env->GetStaticMethodID(classLoaderClazz, "a", "(I)[[B"), (jint)classCount); 




    int cptr = 0;
    for (jsize j = 0; j < classCount; j++)
    {
        jbyteArray classByteArray = env->NewByteArray(classSizes[j]);
        env->SetByteArrayRegion(classByteArray, 0, classSizes[j], (jbyte*)(classes + cptr));
        cptr += classSizes[j];
        env->SetObjectArrayElement(classesData, j, classByteArray);
    }
  
    jint injectResult = env->CallStaticIntMethod(classLoaderClazz, env->GetStaticMethodID(classLoaderClazz, "a", "([[B)I"), classesData);


}

PVOID unload(PVOID arg) {
    HMODULE hm = NULL;
    GetModuleHandleEx(GET_MODULE_HANDLE_EX_FLAG_FROM_ADDRESS |
        GET_MODULE_HANDLE_EX_FLAG_UNCHANGED_REFCOUNT,
        (LPWSTR)&unload, &hm);
    FreeLibraryAndExitThread(hm, 0);

}

typedef void(*Java_org_lwjgl_system_JNI_callP__J)(JNIEnv* env, jclass clazz, jlong lVar);

Java_org_lwjgl_system_JNI_callP__J nglFlush = NULL;

void nglFlush_Hook(JNIEnv* env, jclass clazz, jlong lVar) {
    nglFlush(env, clazz, lVar);
    RemoveHook(nglFlush);
    cheat(env);
    CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE)unload, NULL, 0, NULL);
    return;
}

PVOID WINAPI jvmhooku(PVOID arg) {

    //正常功能开始
    HMODULE jvm = GetModuleHandlePeb(L"lwjgl.dll");

    Java_org_lwjgl_system_JNI_callP__J nglFlush_address = (Java_org_lwjgl_system_JNI_callP__J)GetProcAddressPeb(jvm, "Java_org_lwjgl_system_JNI_callP__J");// 获得入口方法
    SetHook(nglFlush_address, nglFlush_Hook, reinterpret_cast<PVOID*>(&nglFlush));//替换nglFlush方法为Hook方法并保存原始方法函数指针
    return NULL;

}


extern "C" DLL_EXPORT BOOL APIENTRY DllMain(HINSTANCE hinstDLL, DWORD fdwReason, LPVOID lpvReserved)
{
    DisableThreadLibraryCalls(hinstDLL);

    switch (fdwReason)
    {
    case DLL_THREAD_ATTACH:

    case DLL_PROCESS_ATTACH:
        CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE)jvmhooku, NULL, 0, NULL);
        break;
    }
    return TRUE;
}


