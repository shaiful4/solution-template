package com.tigerit.exam;


import static com.tigerit.exam.IO.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * All of your application logic should be placed inside this class.
 * Remember we will load your application from our custom container.
 * You may add private method inside this class but, make sure your
 * application's execution points start from inside run method.
 */
public class Solution implements Runnable {
    
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st, stt;
    String tableName[] = new String[15];
    String columnName[][] = new String[15][105];
    String stn1, stn2;
    int nc[] = new int[15];
    int sia[] = new int[105];
    int ans[][] = new int[105][105];
    int ansColumn[][] = new int[105][5];
    int nt, t1, t2, nac, na;
    
    int NumTableName(String s)
    {
        int i;
        for(i = 0; i < nt; i ++)
        {
            if(tableName[i].equals(s))
                break;
        }
        
        return i;
    }
    
    int NumColumn(int t, String s)
    {
        int i;
        for(i = 0; i < nc[t]; i ++)
        {
            if(columnName[t][i].equals(s))
                break;
        }
        
        return i;
    }
    
    int ShortName(String s)
    {
        if(stn1.equals(s)) return t1;
        else return t2;
    }
    
    void Sort()
    {
        int i, j, k, swap;
        for(i = 0; i < na; i ++)
            sia[i] = i;
        for(i = 0; i < na - 1; i ++)
        {
            for(j = 0; j < na - i - 1; j ++)
            {
                for(k = 0; k < nac; k ++)
                {
                    if(ans[sia[j]][k] > ans[sia[j+1]][k])
                    {
                        swap = sia[j];
                        sia[j] = sia[j+1];
                        sia[j+1] = swap;
                        break;
                    }
                    if(ans[sia[j]][k] < ans[sia[j+1]][k]) break;
                }
            }
        }
    }
    
    void Print()
    {
        int i, j;
        for(i = 0; i < nac; i ++)
            System.out.print(columnName[ansColumn[i][0]][ansColumn[i][1]] + " ");
        System.out.println();
        for(i = 0; i < na; i ++)
        {
            for(j = 0; j < nac; j ++)
                System.out.print(ans[sia[i]][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
    
    @Override
    public void run() {
        
        int testCase, nq, i, j, k, l, t, c1, c2;
        int nd[] = new int[15];
        int data[][][] = new int[15][105][105];
        String s1, s2, s3, s4, s;
        boolean flag;
        
        try{
            testCase = Integer.parseInt(reader.readLine());
            for(t = 1; t <= testCase; t ++)
            {
                flag = true;
                nt = Integer.parseInt(reader.readLine());
                for(i = 0; i < nt; i ++)
                {
                    tableName[i] = reader.readLine();
                    s = reader.readLine();
                    st = new StringTokenizer(s);
                    nc[i] = Integer.parseInt(st.nextToken());
                    nd[i] = Integer.parseInt(st.nextToken());
                    s = reader.readLine();
                    st = new StringTokenizer(s);
                    for(j = 0; j < nc[i]; j ++)
                    {
                        columnName[i][j] = st.nextToken();
                    }
                    for(j = 0; j < nd[i]; j ++)
                    {
                        s = reader.readLine();
                        st = new StringTokenizer(s);
                        for(k = 0; k < nc[i]; k ++)
                            data[i][j][k] = Integer.parseInt(st.nextToken());
                    }
                }
                nq = Integer.parseInt(reader.readLine());
                while(nq != 0)
                {
                    s1 = reader.readLine();
                    s2 = reader.readLine();
                    s3 = reader.readLine();
                    s4 = reader.readLine();
                    s = reader.readLine();
                    
                    st = new StringTokenizer(s2);
                    st.nextToken();
                    s2 = st.nextToken();
                    t1 = NumTableName(s2);
                    if(st.hasMoreTokens())
                    {
                        stn1 = st.nextToken();
                    }
                    
                    st = new StringTokenizer(s3);
                    st.nextToken();
                    s3 = st.nextToken();
                    t2 = NumTableName(s3);
                    if(st.hasMoreTokens())
                    {
                        stn2 = st.nextToken();
                    }
                    
                    st = new StringTokenizer(s1);
                    st.nextToken();
                    s1 = st.nextToken();
                    if(s1.equals("*"))
                    {
                        for(i = 0; i < nc[t1]; i ++)
                        {
                            ansColumn[i][0] = t1;
                            ansColumn[i][1] = i;
                        }
                        for(j = 0; j < nc[t2]; j ++)
                        {
                            ansColumn[i+j][0] = t2;
                            ansColumn[i+j][1] = j;
                        }
                        nac = i + j;
                    }
                    else{
                        for(i = 0; ; i ++)
                        {
                            if(st.hasMoreTokens() == false) break;
                            stt = new StringTokenizer(s1.substring(0, s1.length()-1), ".");
                            ansColumn[i][0] = ShortName(stt.nextToken());
                            ansColumn[i][1] = NumColumn(ansColumn[i][0], stt.nextToken());
                            s1 = st.nextToken();
                        }
                        stt = new StringTokenizer(s1.substring(0, s1.length()), ".");
                        ansColumn[i][0] = ShortName(stt.nextToken());
                        ansColumn[i][1] = NumColumn(ansColumn[i][0], stt.nextToken());
                        nac = i + 1;
                    }
                    
                    st = new StringTokenizer(s4);
                    st.nextToken();
                    s4 = st.nextToken();
                    st.nextToken();
                    s = st.nextToken();
                    
                    st = new StringTokenizer(s4, ".");
                    st.nextToken();
                    s4 = st.nextToken();
                    c1 = NumColumn(t1, s4);
                    
                    st = new StringTokenizer(s, ".");
                    st.nextToken();
                    s = st.nextToken();
                    c2 = NumColumn(t2, s);
                    
                    for(i = l = 0; i < nd[t1]; i ++)
                    {
                        for(j = 0; j < nd[t2]; j ++)
                        {
                            if(data[t1][i][c1] == data[t2][j][c2])
                            {
                                for(k = 0; k < nac; k ++)
                                {
                                    if(ansColumn[k][0] == t1)
                                        ans[l][k] = data[t1][i][ansColumn[k][1]];
                                    else ans[l][k] = data[t2][j][ansColumn[k][1]];
                                }
                                l ++;
                            }
                        }
                    }
                    na = l;
                    Sort();
                    if(flag)
                    {
                        System.out.println("Test: " + t);
                        flag = false;
                    }
                    Print();
                    nq --;
                }
            }
            
        }catch (IOException ex)
        {System.out.println(ex);}
    }
}


