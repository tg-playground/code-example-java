package com.taogen.example.bestpractice.statements;

/**
 * @author Taogen
 */
public class ConditionWithEnum {

    /**
     * Using Enumeration instead of Multiple `if` Statements
     *
     * @param roleName
     * @return
     */
    public static String judge(String roleName){
        return RoleEnum.valueOf(roleName).operations();
    }

    interface RoleOperation {
        String operations();
    }

    enum RoleEnum implements RoleOperation {
        ROLE_ROOT_ADMIN {
            @Override
            public String operations(){
                return "ROLE_ROOT_ADMIN: has AAA permission";
            }
        },
        ROLE_ODER_ADMIN {
            @Override
            public String operations(){
                return "ROLE_ROOT_ADMIN: has BBB permission";
            }
        }
    }
}
