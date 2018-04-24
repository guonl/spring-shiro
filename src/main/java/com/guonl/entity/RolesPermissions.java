package com.guonl.entity;

public class RolesPermissions {
    private Integer id;

    private String roleName;

    private String permission;

    public RolesPermissions(Integer id, String roleName, String permission) {
        this.id = id;
        this.roleName = roleName;
        this.permission = permission;
    }

    public RolesPermissions() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }
}